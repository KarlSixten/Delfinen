package data;

import domain.*;
import domain.comparator.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Database {
    Filehandler filehandler = new Filehandler();
    private CompetitionSwimmer competitionSwimmer;
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
        String[] names = fullName.split("\\s+");
        if (names.length != 2 || names[0].length() < 2 || names[1].length() < 2) {
            return MembershipType.FAILED_CREATE;
        }
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
            if (!membership.isCompetetive()){
            membersArrayList.add(new Coach(fullName, userID, birthDate, email, phoneNumber, address, gender, isActive, isSenior));
            return MembershipType.COACH;}
        }
       return MembershipType.FAILED_CREATE;

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
            if (member.getFullName().equals(fullName) && member.getAddress().equals(address)){
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

    public void loadPerformances(){
        filehandler.loadPerformances(membersArrayList);
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
    public void sortMembers(int choice) {
        Comparator comparator = null;

        switch (choice) {

            case 1 -> comparator = new FullNameComparator();

            case 2 -> comparator = new UserIDComparator();

            case 3 -> comparator = new BirthDateComparator();

            case 4 -> comparator = new EmailComparator();

            case 5 -> comparator = new PhoneNumberComparator();

            case 6 -> comparator = new AddressComparator();

            case 7 -> comparator = new GenderComparator();

            case 8 -> comparator = new IsActiveComparator();

            case 9 -> comparator = new IsSeniorComparator();

            case 10 -> comparator = new IsCompetetiveComparator();

            case 11 -> comparator = new IsCoachComparator();
            default -> {
                System.out.println("hej");
            }
        }
        Collections.sort(membersArrayList, comparator);

    }
    public void sortMembersPrimaryandSec(int choice,int choice2){

        Comparator comparator2 = null;
        Comparator comparator1 = null;

        switch (choice) {
            case 1 -> comparator1 = new FullNameComparator();

            case 2 -> comparator1 = new UserIDComparator();

            case 3 -> comparator1 = new BirthDateComparator();

            case 4 -> comparator1 = new EmailComparator();

            case 5 -> comparator1 = new PhoneNumberComparator();

            case 6 -> comparator1 = new AddressComparator();

            case 7 -> comparator1 = new GenderComparator();

            case 8 -> comparator1 = new IsActiveComparator();

            case 9 -> comparator1 = new IsSeniorComparator();

            case 10 -> comparator1 = new IsCompetetiveComparator();

            case 11 -> comparator1 = new IsCoachComparator();

            default -> {
                System.out.println("Hej");
            }
        }
        switch (choice2) {
            case 1 -> comparator2 = new FullNameComparator();

            case 2 -> comparator2 = new UserIDComparator();

            case 3 -> comparator2 = new BirthDateComparator();

            case 4 -> comparator2 = new EmailComparator();

            case 5 -> comparator2 = new PhoneNumberComparator();

            case 6 -> comparator2 = new AddressComparator();

            case 7 -> comparator2 = new GenderComparator();

            case 8 -> comparator2 = new IsActiveComparator();

            case 9 -> comparator2 = new IsSeniorComparator();

            case 10 -> comparator2 = new IsCompetetiveComparator();

            case 11 -> comparator2 = new IsCoachComparator();

            default -> {
                System.out.println("hej");
            }
        }
        Collections.sort(membersArrayList, comparator1.thenComparing(comparator2));
    }

    public Member registerPerformanceForCompetetionswimmer(String fullName, String address, String category, double performanceTime, boolean timeMadeInCompetition, int year, int month, int dayOfMonth) {
        for (Member member : membersArrayList) {
            if (member != null && member.getMembership() != null && member.getAddress() != null) {
                if (member.getMembership().isCompetetive() &&
                        fullName.toLowerCase().contains(member.getFullName().toLowerCase()) &&
                        member.getAddress().toLowerCase().contains(address.toLowerCase())) {
                    ((CompetitionSwimmer)member).registerPerformance(category,performanceTime,timeMadeInCompetition,year,month,dayOfMonth);
                    return member;
                }
            }
        }
        return null;
    }


    public void registerPerformance(Member member, String category, double performanceTime, boolean timeMadeInCompetition,int year, int month, int dayOfMonth){

        ((CompetitionSwimmer) member).registerPerformance(category,performanceTime,timeMadeInCompetition,year,month,dayOfMonth);
}

public ArrayList<Performance> viewPerformances(Member member){
        return ((CompetitionSwimmer) member).viewPerfomances();
}
public void savePerformances(){
        filehandler.savePerformance(membersArrayList);
}

    public String listOfCompetetionsSwimmersByName(String name) {

        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        for (Member member : membersArrayList) {
            if (member instanceof CompetitionSwimmer && member.getFullName().toLowerCase().trim().contains(name.toLowerCase().trim())) {
                stringBuilder.append(index).append(" ").append(member).append("\n");
                index += 1;
            }
        }

        return  stringBuilder.toString();
    }

    public Member getCompetetionSwimmerInListByIndex(int index, String name){
        ArrayList<Member> competitionSwimmers = new ArrayList<>();
        for (Member member : membersArrayList) {
            if (member instanceof CompetitionSwimmer && member.getFullName().toLowerCase().trim().contains(name.toLowerCase().trim())){
                competitionSwimmers.add(member);
            }
        }
        if (membersArrayList.size()<index){
            return null;
        }
        return competitionSwimmers.get(index-1);
    }

    public ArrayList<Performance> sortPerformance(int choice, int choice2) {
        ArrayList<Performance> performances = new ArrayList<>();
        Comparator comparator = null;

        switch (choice) {
            case 1:
                comparator = new GenderComparator();
                break;
            case 2:
                comparator = new GenderComparator().reversed();
                break;
        }

        switch (choice2) {
            case 1, 2, 3, 4:
                String targetCategory = getCategoryBasedOnChoice(choice2);

                for (Member member : membersArrayList) {
                    if (member instanceof CompetitionSwimmer) {
                        for (Performance performance : ((CompetitionSwimmer) member).getPerformances()) {
                            if (performance.getCategory().equalsIgnoreCase(targetCategory)) {
                                performance.setMadeBy(member.getFullName());
                                performance.setGender(member.getGender());
                                performances.add(performance);
                            }
                        }
                    }
                }
        }

        Collections.sort(performances, comparator.thenComparing(new PerformanceTimeComparator()));
        return performances;
    }
    private String getCategoryBasedOnChoice(int choice2) {
        switch (choice2) {
            case 1: return "butterfly";
            case 2: return "crawl";
            case 3: return "rygcrawl";
            case 4: return "bryst";
            default: return "";
        }
    }


    public ArrayList<Performance> getTop5Swimmers(int choice1, int choice2) {
        ArrayList<Performance> top5Performance = new ArrayList<>();
        ArrayList<String> addedSwimmerIDs = new ArrayList<>();

        for (Performance performance : sortPerformance(choice1, choice2)) {
            String swimmerID = performance.getMadeBy();

            if (top5Performance.size() < 5 && !addedSwimmerIDs.contains(swimmerID)) {
                top5Performance.add(performance);
                addedSwimmerIDs.add(swimmerID);
            }
        }
        return top5Performance;
    }

}


