package domain;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Random;

public class Member {



    private String fullName;
    private String userID;
    private LocalDate birthDate;
    private String email;
    private int phoneNumber;
    private String address;
    private String gender;

    public Membership getMembership() {
        return membership;
    }

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

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public String getUserID() {
        return userID;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Member: " +
                "fullName='" + fullName + '\'' +
                ", userID='" + userID + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'';
    }
    public String toCSVString(){
        return fullName + ";" + userID + ";"  + birthDate + ";" + email + ";" + phoneNumber + ";" + address + ";" + gender + ";" + membership.toCSVString();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public int calculateSubscriptionPrice() {
        int price = 0;
        if (!membership.isActive()) {
            price = 500;
        } else if (Period.between(birthDate, LocalDate.now()).getYears() >= 60) {
            price = 1200;
        } else if (membership.isSenior()) {
            price = 1600;
        } else if (!membership.isSenior()) {
            price = 1000;
        }
        return price;
    }

    }