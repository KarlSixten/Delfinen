package domain;

import data.Database;

import java.time.LocalDate;

import java.io.IOException;

public class Controller {
    private Database database;

    public void createNewUser(String fullName,
                              LocalDate birthDate,
                              String email,
                              int phoneNumber,
                              String address,
                              String gender,
                              boolean isActive,
                              boolean isSenior,
                              boolean isCompetitive,
                              boolean isCoach) {
        database.createNewUser(fullName, birthDate, email, phoneNumber, address, gender, isActive, isSenior, isCompetitive, isCoach);
    }


    public String getAllMemberNames() {
        return database.getAllMemberNames();
    }
    public Controller() throws IOException{
        this.database = new Database();
    }
    public void loadData(){
        database.saveMembers();
    }
}
