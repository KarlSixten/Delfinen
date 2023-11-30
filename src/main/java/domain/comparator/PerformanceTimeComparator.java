package domain.comparator;

import domain.CompetitionSwimmer;

import java.util.Comparator;

public class PerformanceTimeComparator implements Comparator<CompetitionSwimmer> {
    public int compare (CompetitionSwimmer c1, CompetitionSwimmer c2){
return Double.compare((c1.getPerformance().getPerformanceTime()), c2.getPerformance().getPerformanceTime());
    }
}


