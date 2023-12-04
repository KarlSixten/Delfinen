package data;

import domain.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {
    private File memberFileList = new File("Datasheet.csv");
    private File performanceList = new File ("PerformanceList.csv");

    public ArrayList<Member> loadData() throws  IOException{
        ArrayList<Member> memberFileList = new ArrayList<>();
        Scanner scanner = new Scanner(new File("Datasheet.csv"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(";");
            if (values[10].equals("true")) {
                {
                    memberFileList.add(new Coach(values[0],
                            values[1],
                            LocalDate.parse(values[2]),
                            values[3],
                            Integer.parseInt(values[4]),
                            values[5],
                            values[6],
                            Boolean.parseBoolean(values[7]),
                            Boolean.parseBoolean(values[8])));
                }

            } else if (values[9].equals("true")) {
                {
                    memberFileList.add(new CompetitionSwimmer(values[0],
                            values[1],
                            LocalDate.parse(values[2]),
                            values[3],
                            Integer.parseInt(values[4]),
                            values[5],
                            values[6],
                            Boolean.parseBoolean(values[7]),
                            Boolean.parseBoolean(values[8]),
                            Boolean.parseBoolean(values[9]),
                            Boolean.parseBoolean(values[10])));
                }
            } else

            {
                    memberFileList.add(new Member(values[0],
                            values[1],
                            LocalDate.parse(values[2]),
                            values[3],
                            Integer.parseInt(values[4]),
                            values[5],
                            values[6],
                            new Membership(Boolean.parseBoolean(values[7]), Boolean.parseBoolean(values[8]), Boolean.parseBoolean(values[9]), Boolean.parseBoolean(values[10]))));
                }


        }
        return memberFileList;
    }
    public void saveMembers (ArrayList<Member> database){
        ArrayList<Member> members = database;
        PrintStream output;
        try {
            output = new PrintStream(memberFileList);
            for (Member member : members){
                output.println(member.toCSVString());
            }
            output.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void savePerformance(ArrayList<Member> performanceDatabase){
        ArrayList<Member> competitionSwimmers = performanceDatabase;
        PrintStream output;
        try {
            output = new PrintStream(performanceList);
            for (Member member: competitionSwimmers){
                if (member instanceof CompetitionSwimmer){
                    if (((CompetitionSwimmer) member).getPerformances().size()>0) {
                        output.println(((CompetitionSwimmer) member).toPerformanceCSVString2());
                    }
                }
                }output.close();
        } catch (IOException e){
            e.printStackTrace();
        }
            }
            public void loadPerformances(ArrayList<Member> memberArrayList){
                Scanner scanner = null;
                try {
                    scanner = new Scanner(new File("PerformanceList.csv"));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(";");
            for (Member member : memberArrayList) {
                if (member.getMembership().isCompetetive()){
                    if (member.getFullName().equals(values[0]) && member.getAddress().equals(values[1])){
                        ((CompetitionSwimmer)member).registerPerformance(values[2],Double.parseDouble(values[3]),Boolean.parseBoolean(values[4]),LocalDate.parse(values[5]));
                    }
                }
            }
        }

            }

}
