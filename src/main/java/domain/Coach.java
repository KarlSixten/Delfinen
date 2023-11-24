package domain;

import domain.Member;

import java.time.LocalDate;

public class Coach extends Member {

    public Coach(String fullName, String userID, LocalDate birthDate, String email, int phoneNumber, String address, String gender, boolean isActive, boolean isSenior) {
        super(fullName,userID , birthDate, email, phoneNumber, address, gender, new Membership(isActive, true, false, true));
    }
}
