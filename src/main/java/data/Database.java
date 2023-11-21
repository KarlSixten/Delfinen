package data;

import domain.Member;
import domain.Membership;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Database {
    private ArrayList<Member> membersArrayList = new ArrayList<>();

    private void createNewUser(String fullName, LocalDate birthDate, String email, int phoneNumber, String address, String gender, Membership membership) {
        String userID = createUserID(fullName);
        membersArrayList.add(new Member(fullName, userID, birthDate, email, phoneNumber, address, gender, membership));

    }

    public String createUserID(String fullName) {
        String userID;
        String[] names = fullName.split("\\s+");
        String userIDLetters = names[0].substring(0, 2).toLowerCase() +
                names[names.length - 1].substring(0, 2).toLowerCase();
        Random random = new Random();
        int numbers = random.nextInt(10000);
        userID = String.format("%s%04d", userIDLetters, numbers);
        for (Member member : membersArrayList) {
            if (member.getUserID().matches(userID)) {
                userID = createUserID(fullName);
            }
        }
        return userID;
    }
}
