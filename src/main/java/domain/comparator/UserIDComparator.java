package domain.comparator;

import domain.Member;

import java.util.Comparator;

public class UserIDComparator implements Comparator<Member> {
    public int compare(Member m1, Member m2){
        return m1.getUserID().compareTo(m2.getUserID());
    }
}
