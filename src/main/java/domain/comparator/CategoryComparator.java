package domain.comparator;

import domain.CompetitionSwimmer;
import domain.Performance;

import java.util.Comparator;

public class CategoryComparator implements Comparator<Performance> {
    public int compare (Performance p1, Performance p2){
    return  p1.getCategory().compareTo(p2.getCategory());
    }
}
