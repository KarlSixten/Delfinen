package domain.comparator;

import domain.Member;

import java.util.Comparator;

public class FullNameComparator implements Comparator<Member> {
    public int compare (Member m1, Member m2){
        return m1.getFullName().compareTo(m2.getFullName());
    }
}
