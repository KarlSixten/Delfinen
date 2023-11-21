package domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitionSwimmer extends Member{
    private String category;
    private Coach coach;


    public CompetitionSwimmer(String fullName, String userID, LocalDate birthDate, String email,
                              int phoneNumber, String address, String gender, Membership membership){
        super(fullName, userID, birthDate, email,
         phoneNumber, address, gender, membership);
    }
}
