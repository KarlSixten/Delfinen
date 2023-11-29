package domain.comparator;

import domain.CompetitionSwimmer;

import java.util.Comparator;

public class CategoryComparator implements Comparator<CompetitionSwimmer> {
    public int compare (CompetitionSwimmer c1, CompetitionSwimmer c2){
    return  c1.getPerformance().getCategory().compareTo(c2.getPerformance().getCategory());
    }
}
