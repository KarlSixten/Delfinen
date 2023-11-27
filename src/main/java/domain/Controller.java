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
    public String listOfCoaches(){
        return database.listOfCoaches();
    }

    public Member findfirstMember(String fullName, String address){
        return database.findfirstMember(fullName, address);
    }

    public void setCoachToMember(Member competitionSwimmer, Member coach){
        database.setCoachToMember(competitionSwimmer,coach);
    }
    public Member getIndexInListOfCoaches(int index){
        return database.getCoachInListByIndex(index);
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
    public void sortMember(int choice){
        database.sortMembers(choice);
    }
    public void sortMemberPrimaryandSecundary(int choice, int choice2){
        database.sortMembersPrimaryandSec(choice, choice2);
    }
    public Member findCompetitionSwimmer(String fullName, String addresse){
        return database.findCompetitionSwimmer(fullName,addresse);
    }
    public void registerPerformance(Member member, String category, double performanceTime, boolean timeMadeInCompetition, int year, int month, int dayOfMonth){
        database.registerPerformance(member,category,performanceTime,timeMadeInCompetition,year,month,dayOfMonth);
    }

    public ArrayList<Performance> viewPerformances(Member member){
        return database.viewPerformances(member);
    }
    public void savePerformance(){
        database.savePerformances();
    }
}
