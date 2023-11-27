package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void setTeam(String team) {
        this.team = team;
    }
}
