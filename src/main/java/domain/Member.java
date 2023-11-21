package domain;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

public class Member {

    private String fullName;
    private String userID;
    private LocalDate birthDate;
    private String email;
    private int phoneNumber;
    private String address;
    private String gender;
    private Membership membership;

    public Member(String fullName, String userID, LocalDate birthDate, String email, int phoneNumber, String address, String gender, Membership membership) {
        this.fullName = fullName;
        this.userID = userID;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.membership = membership;
    }

    public String getUserID() {
        return userID;
    }

    public boolean validUserID() {
        return userID.matches("^[a-z]{4}\\d{4}$");
    }

    public String createUserID() {
        String[] names = fullName.split("\\s+");
        String userIDLetters = names[0].substring(0, 2).toLowerCase() +
                names[names.length - 1].substring(0, 2).toLowerCase();
        Random random = new Random();
        int numbers = random.nextInt(10000);
        return userID = String.format("%s%04d", userIDLetters, numbers);
    }

    @Override
    public String toString() {
        return "Member{" +
                "fullName='" + fullName + '\'' +
                ", userID='" + userID + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
    public String toCSVString(){
        return fullName + ";" + userID + ";" + birthDate + ";" + birthDate + ";" + email + ";" + phoneNumber + ";" + address + ";" + gender + ";";
    }
}