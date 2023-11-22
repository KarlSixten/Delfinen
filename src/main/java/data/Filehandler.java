package data;

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

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(";");

            {
                memberFileList.add(new Member(values[0],
                        values[1],
                        LocalDate.parse(values[2]),
                        values[3],
                        Integer.parseInt(values[4]),
                        values[5],
                        values[6],
                        Membership.parse(values[7])));
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





}
