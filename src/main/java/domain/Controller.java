package domain;

import data.Database;

import java.time.LocalDate;

public class Controller {
    private final Database database = new Database();

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
}
