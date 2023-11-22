package domain;

import data.Database;

import java.util.ArrayList;

public class Controller {
    private final Database database = new Database();
    public String getAllMemberNames() {
        return database.getAllMemberNames();
    }

    public ArrayList<Member> findMembers(String search){
       return database.findMembers(search);
    }

    public Member getMemberFromIndex(int choice, ArrayList<Member> foundMembers){
        return database.getMemberFromIndex(choice, foundMembers);
    }
}
