package domain.comparator;

import domain.Member;

import java.util.Comparator;

public class EmailComparator implements Comparator <Member> {
    public int compare (Member m1, Member m2){
        return m1.getEmail().compareTo(m2.getEmail());
    }
}
