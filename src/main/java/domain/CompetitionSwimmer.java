package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompetitionSwimmer extends Member {
    private Coach coach;
    private ArrayList<Performance> performances = new ArrayList<>();
    public CompetitionSwimmer(String fullName, String userID, LocalDate birthDate, String email,
                              int phoneNumber, String address, String gender,boolean isActive, boolean isSenior) {
        super(fullName, userID, birthDate, email,
                phoneNumber, address, gender, new Membership(isActive, isSenior,true,false));
    }

    public void registerPerformance(String category, double performanceTime){
        performances.add(new Performance(category, performanceTime));
    }


}
