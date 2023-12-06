package domain;

import data.Database;

import java.io.IOException;
import java.util.ArrayList;

import java.time.LocalDate;

public class Controller {
    private final Database database;

    public Controller(String filename) throws IOException {
        this.database =  new Database(filename);
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

    public void saveAccountantList(){
        database.saveAccountantList();
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

    public void loadAccountantList(){
        database.loadAccountantList();
    }

    public void setHasPaidForMember(Member member, boolean choice){
        database.setHasPaidForMember(member,choice);
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

    public void registerPerformance(Member member, String category, double performanceTime, boolean timeMadeInCompetition, LocalDate dateForPerformance){
        database.registerPerformance(member, category,performanceTime,timeMadeInCompetition,dateForPerformance);
    }

    public String listOfCompetetionsSwimmersByName(String name){
        return database.listOfCompetetionsSwimmersByName(name);
    }
    public Member getCompetetionSwimmerInListByIndex(int index, String name){
        return database.getCompetetionSwimmerInListByIndex(index, name);
    }    public void loadPerformances(){
        database.loadPerformances();
    }

    public void savePerformance(){
        database.savePerformances();
    }
    public ArrayList<Performance> sortPerformance(int choice1, int choice2){
        return database.sortPerformance(choice1,choice2);
    }

    public ArrayList<Performance> getTop5Swimmers(int  choice1, int choice2){
        return database.getTop5Swimmers(choice1,choice2);
    }

    public ArrayList<Performance> getOneSwimmersPerformances(Member member, SwimDiscipline swimDiscipline){
        return database.getOneSwimmersPerformances(member,swimDiscipline);
    }

    public int getTotalSubscriptionIncome() {
        return database.getTotalSubscriptionIncome();
    }

    public String arrearsList(){
        return database.arrearsList();
    }
}
