package domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Performance {
    private String category;
    private double performanceTime;
    private boolean timeMadeInCompetition;
    private LocalDate dateTimeIsMade;
    private String madeBy;
    private String gender;


    public Performance(String category, double performanceTime, boolean timeMadeInCompetition, LocalDate dateTimeIsMade){
        this.category = category;
        this.performanceTime = performanceTime;
        this.timeMadeInCompetition = timeMadeInCompetition;
        this.dateTimeIsMade = dateTimeIsMade;

    }

    @Override
    public String toString() {
        return
                        " Navn: " + madeBy +
                        ", Kategori: " + category +
                        ", Tid: " + performanceTime +
                        ", Lavet i konkurrence: " + timeMadeInCompetition +
                        ", Dato: " + dateTimeIsMade +
                        ", Køn: " + gender + "\n";
    }

    public String performanceCSV(){
        return category + ";" + performanceTime + ";" + timeMadeInCompetition + ";" + dateTimeIsMade;
    }

    public String getCategory() {
        return category;
    }

    public double getPerformanceTime() {
        return performanceTime;
    }

    public boolean getIsTimeMadeInCompetition() {
        return timeMadeInCompetition;
    }

    public LocalDate getDateTimeIsMade() {
        return dateTimeIsMade;
    }

    public String getMadeBy(){
        return madeBy;
    }
    public String getGender(){
        return gender;
    }
    public void setMadeBy(String madeBy){
        this.madeBy = madeBy;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
}
