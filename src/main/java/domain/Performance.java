package domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Performance {
    private String category;
    private double performanceTime;
    private boolean timeMadeInCompetition;

    private LocalDate dateTimeIsMade;



    public Performance(String category, double performanceTime, boolean timeMadeInCompetition, LocalDate dateTimeIsMade){
        this.category = category;
        this.performanceTime = performanceTime;
        this.timeMadeInCompetition = timeMadeInCompetition;
        this.dateTimeIsMade = dateTimeIsMade;

    }
    @Override
    public String toString() {
        return "Performance{" +
                "category='" + category + '\'' +
                ", performanceTime=" + performanceTime +
                ", timeMadeInCompetition=" + timeMadeInCompetition +
                ", dateTimeIsMade=" + dateTimeIsMade +
                '}' + "\n";
    }

    public String performanceCSV(){
        return category + ";" + performanceTime + ";" + timeMadeInCompetition + ";" + dateTimeIsMade;
    }

}
