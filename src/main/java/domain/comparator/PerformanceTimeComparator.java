package domain.comparator;

import domain.CompetitionSwimmer;
import domain.Performance;

import java.util.Comparator;

public class PerformanceTimeComparator implements Comparator<Performance> {
    public int compare (Performance p1, Performance p2){
return Double.compare((p1.getPerformanceTime()), p2.getPerformanceTime());
    }
}


