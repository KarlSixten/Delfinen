package domain;

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



    public String toCSVString() {
        return
                isActive + ";" +
                isSenior + ";" +
                isCompetetive + ";" +
                isCoach;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isSenior() {
        return isSenior;
    }

    public boolean isCompetetive() {
        return isCompetetive;
    }

    public boolean isCoach() {
        return isCoach;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCompetetive(boolean competetive) {
        isCompetetive = competetive;
    }

    public void setCoach(boolean coach) {
        isCoach = coach;
    }
}

