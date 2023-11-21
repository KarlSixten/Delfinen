package domain;

import java.time.LocalDate;

public class Membership {
private boolean isActive;
private boolean isSenior;
private boolean isCompetetive;
private boolean isCoach;

public Membership(boolean isActive, boolean isSenior, boolean isCompetitive, boolean isCoach) {
    this.isActive = isActive;
    this.isSenior = isSenior;
    this.isCompetetive = isCompetitive;
    this.isCoach = isCoach;

}
}
