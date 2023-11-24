package domain.comparator;

import domain.Member;

import java.util.Comparator;

public class IsCoachComparator implements Comparator<Member> {
    public int compare (Member m1, Member m2){
        return Boolean.compare(m1.getMembership().isCoach(),m2.getMembership().isCoach());
    }
}

