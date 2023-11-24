package domain.comparator;

import domain.Member;

import java.util.Comparator;

public class BirthDateComparator implements Comparator<Member> {
    public int compare(Member m1, Member m2){
        return m1.getBirthDate().compareTo(m2.getBirthDate());
    }
}
