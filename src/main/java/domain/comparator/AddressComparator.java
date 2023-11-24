package domain.comparator;

import domain.Member;

import java.util.Comparator;


    public class AddressComparator implements Comparator <Member> {
        public int compare (Member m1, Member m2){
            return m1.getAddress().compareTo(m2.getAddress());
        }
    }


