package domain;
import java.time.LocalDate;
import java.time.Period;

public class Member {
    private String fullName;
    private String userID;
    private LocalDate birthDate;
    private String email;
    private int phoneNumber;
    private String address;
    private String gender;
    private boolean hasPaid;

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
        this.hasPaid = true;
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
                "Navn: " + fullName +
                ", UserID: '" + userID +
                ", Fødselsdag: " + birthDate +
                ", Email: " + email  +
                ", Telefonnummer: " + phoneNumber +
                ", addresse: " + address+
                ", køn: " + gender;
    }
    public String toCSVString(){
        return fullName + ";" + userID + ";"  + birthDate + ";" + email + ";" + phoneNumber + ";" + address + ";" + gender + ";" + membership.toCSVString();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public void setHasPaid(boolean hasPaid){
        this.hasPaid = hasPaid;
    }

    public String toAccountantCSVString(){
        return  userID+
                ";" + email+
                ";" +phoneNumber +
                ";" + address+
                ";" + hasPaid;
    }
    public boolean getHasPaid(){
        return hasPaid;
    }
}

