package domain;

import data.Database;

import java.io.IOException;
import java.util.ArrayList;

import java.time.LocalDate;

public class Controller {
    private final Database database;

    public Controller() throws IOException {
        this.database =  new Database();
    }
public ArrayList<Member> getArrayList (){
        return database.getMembersArrayList();
    }

    public MembershipType createNewUser(String fullName,
                              LocalDate birthDate,
                              String email,
                              int phoneNumber,
                              String address,
                              String gender,
                              boolean isActive,
                              boolean isSenior,
                              boolean isCompetitive,
                              boolean isCoach) {
        return database.createNewUser(fullName, birthDate, email, phoneNumber, address, gender, isActive, isSenior, isCompetitive, isCoach);
    }


    public String getAllMemberNames() {
        return database.getAllMemberNames();
    }

    public ArrayList<Member> findMembers(String search){
       return database.findMembers(search);
    }

    public Member getMemberFromIndex(int choice, ArrayList<Member> foundMembers){
        return database.getMemberFromIndex(choice, foundMembers);
    }
    public void saveData(){
        database.saveMembers();
    }

    public String getMemberName(Member member){
        return member.getFullName();
    }
    public void deleteMember(Member member){
database.deleteMember(member);

    }
}
