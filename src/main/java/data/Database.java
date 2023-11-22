package data;

import domain.Member;
import domain.Membership;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Database {
    private ArrayList<Member> membersArrayList = new ArrayList<>();

    private void createNewUser(String fullName,
                               LocalDate birthDate,
                               String email, int phoneNumber,
                               String address,
                               String gender,
                               boolean isActive,
                               boolean isSenior,
                               boolean isCompetitive,
                               boolean isCoach) {
        String userID = createUserID(fullName);
        Membership membership = new Membership(isActive, isSenior, isCompetitive, isCoach);
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

    public String getAllMemberNames() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Member member : membersArrayList) {
            stringBuilder.append(member.getFullName() + "\n");
        }
        return stringBuilder.toString();
    }

    public ArrayList<Member> findMembers(String search){
        ArrayList<Member> foundMembers = new ArrayList<>();
        for (Member member: membersArrayList) {
            if (search.contains(member.getFullName()) || search.contains(member.getUserID()) || search.contains(Integer.toString(member.getPhoneNumber()))){
              foundMembers.add(member);
            }
        }
    return foundMembers;
    }

    public Member getMemberFromIndex(int choice, ArrayList<Member> foundMembers){
        Member selectedMember = null;
        if (0 < choice && choice <= foundMembers.size()){
            selectedMember = foundMembers.get(choice-1);
        }
        return selectedMember;
    }

}
