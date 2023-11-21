package domain;

import java.time.LocalDate;

public class Membership {
private boolean active;
private boolean senior;
private boolean competitive;
private boolean coach;

public Membership(boolean active, boolean senior, boolean competitive, boolean coach) {
    this.active = active;
    this.senior = senior;
    this.competitive = competitive;
    this.coach = coach;

}
}
