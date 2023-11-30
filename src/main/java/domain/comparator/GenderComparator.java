package domain.comparator;
import domain.CompetitionSwimmer;
import domain.Member;
import domain.Performance;

import java.util.Comparator;


public class GenderComparator implements Comparator <Performance> {
    public int compare (Performance p1, Performance p2){
        return p1.getGender().compareTo(p2.getGender());
    }
}


