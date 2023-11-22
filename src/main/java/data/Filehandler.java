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
