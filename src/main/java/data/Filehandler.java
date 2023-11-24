package data;

import domain.Coach;
import domain.CompetitionSwimmer;
import domain.Member;
import domain.Membership;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {
    private File memberFileList = new File("Datasheet.csv");

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

    public void loadMembers (ArrayList<Member> database){
    Scanner scanner;
    try {
        scanner = new Scanner(memberFileList);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(";");
            if (values.length == 11){
                String fullName = values [0];
                String userID = values [1];
                LocalDate birthDate = LocalDate.parse(values[2]);
                String email = values [3];
                int phoneNumber = Integer.parseInt(values[4]);
                String address = values [5];
                String gender = values [6];

                boolean isActive = Boolean.parseBoolean(values[7]);
                boolean isSenior = Boolean.parseBoolean(values[8]);
                boolean isCompetitive = Boolean.parseBoolean(values[9]);
                boolean isCoach = Boolean.parseBoolean(values [10]);

                Membership membership = new Membership( isActive,  isSenior,  isCompetitive,  isCoach);

                Member member = new Member(fullName, userID,birthDate,email,phoneNumber,address,gender,membership);
                database.add(member);
            }

        }
    }catch (IOException e){
        e.printStackTrace();
    }

}
}
