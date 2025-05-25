package Daniel_Niv;

import java.util.Comparator;

public class CompareNumOfMembers implements Comparator<Commission> {

    @Override
    public int compare(Commission c1, Commission c2) {
        return Integer.compare(c1.getNumOfCommissionMembers(), c2.getNumOfCommissionMembers());
    }
}
