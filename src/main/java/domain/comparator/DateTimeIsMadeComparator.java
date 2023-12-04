package domain.comparator;

import domain.CompetitionSwimmer;
import domain.Performance;

import java.util.Comparator;

public class DateTimeIsMadeComparator implements Comparator<Performance> {
        public int compare (Performance p1, Performance p2){
            return  p1.getDateTimeIsMade().compareTo(p2.getDateTimeIsMade());
        }
    }


