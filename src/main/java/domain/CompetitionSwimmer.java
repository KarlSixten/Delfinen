package domain;

import java.time.LocalDate;
import java.util.ArrayList;


public class CompetitionSwimmer extends Member {
    private Coach coach;
    private ArrayList<Performance> performances = new ArrayList<>();

    private String team;
    public CompetitionSwimmer(String fullName, String userID, LocalDate birthDate, String email,
                              int phoneNumber, String address, String gender,boolean isActive, boolean isSenior, boolean isCompetitive, boolean isCoach) {
        super(fullName, userID, birthDate, email,
                phoneNumber, address, gender, new Membership(isActive, isSenior,isCompetitive,isCoach));
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public void registerPerformance(String category, double performanceTime, boolean performedInCompetition, int year, int month, int dayOfMonth ){
            performances.add(new Performance(category, performanceTime, performedInCompetition, LocalDate.of(year, month, dayOfMonth)));
    }

    public void registerPerformance(String category, double performanceTime, boolean performedInCompetition,LocalDate localDate){
        performances.add(new Performance(category, performanceTime, performedInCompetition, localDate));
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public ArrayList<Performance> viewPerfomances(){
        return performances;
    }
    public String toPerformanceCSVString(){
        String output = "";
        for (Performance performance: performances) {
            output = getFullName() + ";" + getAddress() + ";" + performance.performanceCSV() + "\n";
        }
        return output;
    }
    public String toPerformanceCSVString2() {
        StringBuilder output = new StringBuilder();
        int iterator = 1;
        for (Performance performance : performances) {
            if (performances.size()>(iterator)) {
                output.append(getFullName()).append(";").append(getAddress()).append(";").append(performance.performanceCSV()).append("\n");
                iterator += 1;
            }
            else output.append(getFullName()).append(";").append(getAddress()).append(";").append(performance.performanceCSV());
        }

        return output.toString();
    }

    public ArrayList<Performance> getPerformances(){
        return performances;
    }


}
