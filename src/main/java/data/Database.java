package data;

import domain.Member;
import domain.Membership;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Database {
    Filehandler filehandler = new Filehandler();
    private final ArrayList<Member> membersArrayList = new ArrayList<>(1);

    public Database() throws IOException{
        setMembersArrayList(filehandler.loadData());

    }
public void setMembersArrayList(ArrayList<Member> liste){
        membersArrayList.addAll(liste);
}
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
    public void saveMembers(){
        filehandler.saveMembers(membersArrayList);
    }

}
