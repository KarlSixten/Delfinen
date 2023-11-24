package domain.comparator;
import domain.Member;
import java.util.Comparator;


public class GenderComparator implements Comparator <Member> {
    public int compare (Member m1, Member m2){
        return m1.getGender().compareTo(m2.getGender());
    }
}


