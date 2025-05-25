package Daniel_Niv;

import java.util.Comparator;

public class Comparebyarticles implements Comparator<Commission> {

    @Override
    public int compare(Commission o1, Commission o2) {
        int sum1=0;
        int sum2=0;
        for (int i=0;i<o1.getNumOfCommissionMembers();i++){
            if(o1.getCommissionTeam()[i] instanceof Phd){
                sum1+=((Phd) o1.getCommissionTeam()[i]).getNumOfArticles();
            }
        }
        for (int i=0;i<o2.getNumOfCommissionMembers();i++){
            if(o2.getCommissionTeam()[i] instanceof Phd){
                sum2+=((Phd) o2.getCommissionTeam()[i]).getNumOfArticles();
            }
        }
        return Integer.compare(sum1,sum2);
    }
}
