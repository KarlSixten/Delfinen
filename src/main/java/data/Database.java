package data;

import domain.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Database {
    Filehandler filehandler = new Filehandler();

    public ArrayList<Member> getMembersArrayList() {
        return membersArrayList;
    }

    private ArrayList<Member> membersArrayList = new ArrayList<>();

    public Database() throws IOException {
        setMembersArrayList(filehandler.loadData());
    }

    public void setMembersArrayList(ArrayList<Member> liste) {
        membersArrayList.addAll(liste);
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
        String userID = createUserID(fullName);
        Membership membership = new Membership(isActive, isSenior, isCompetitive, isCoach);
        if (!membership.isCoach()) {
            if (!membership.isCompetetive()) {
                membersArrayList.add(new Member(fullName, userID, birthDate, email, phoneNumber, address, gender, membership));
                return MembershipType.MEMBER;
            } else
                membersArrayList.add(new CompetitionSwimmer(fullName, userID, birthDate, email, phoneNumber, address, gender, isActive, isSenior, isCompetitive, isCoach));
            return MembershipType.COMPETITIVE;
        } else {
            membersArrayList.add(new Coach(fullName, userID, birthDate, email, phoneNumber, address, gender, isActive, isSenior));
            return MembershipType.COACH;
        }

    }

    public String listOfCoaches() {

        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        for (Member member : membersArrayList) {
            if (member instanceof Coach) {
                stringBuilder.append(index).append(" ").append(member).append("\n");
                index += 1;
            }
        }

        return  stringBuilder.toString();
    }

    public Member getCoachInListByIndex(int index){
        ArrayList<Member> coaches = new ArrayList<>();
        for (Member member : membersArrayList) {
            if (member instanceof Coach){
                coaches.add(member);
            }
        }
        return coaches.get(index-1);
    }

    public void setCoachToMember(Member competitionswimmer, Member coach){
        if (competitionswimmer instanceof CompetitionSwimmer){
            ((CompetitionSwimmer) competitionswimmer).setCoach((Coach) coach);

        }    }

    public Member findfirstMember(String fullName, String address){
        for (Member member: membersArrayList) {
            if (member.getFullName().equals(fullName) && member.getUserID().equals(address)){
                return member;
            }
        }
        return null;
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

    public ArrayList<Member> findMembers(String search) {
        ArrayList<Member> foundMembers = new ArrayList<>();
        for (Member member : membersArrayList) {
            if (member.getFullName().toLowerCase().contains(search.toLowerCase()) || search.contains(member.getUserID()) || search.contains(Integer.toString(member.getPhoneNumber()))) {
                foundMembers.add(member);
            }
        }
        return foundMembers;
    }

    public Member getMemberFromIndex(int choice, ArrayList<Member> foundMembers) {
        Member selectedMember = null;
        if (0 < choice && choice <= foundMembers.size()) {
            selectedMember = foundMembers.get(choice - 1);
        }
        return selectedMember;
    }

    public void saveMembers() {
        filehandler.saveMembers(membersArrayList);
    }
    public void deleteMember(Member member){
        int index = membersArrayList.indexOf(member);
        membersArrayList.remove(index);
    }
}
