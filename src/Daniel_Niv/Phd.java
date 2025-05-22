package Daniel_Niv;

public class Phd extends Lecturer implements Comparable<Phd>{
    protected int numOfArticles;

    public Phd(String name, String id, String degreeName, int salary, eDegree degree,int numOfArticles) {
        super(name, id, degreeName, salary, degree);
        this.numOfArticles=numOfArticles;
    }

    public int getNumOfArticles() {
        return numOfArticles;
    }

    @Override
    public int compareTo(Phd o) {
//       if(numOfArticles<o.numOfArticles){
//           return -1;
//       } else if (numOfArticles==o.numOfArticles) {
//         return 0;
//       }
//       return 1;
        Integer first=this.numOfArticles;
        Integer sec=o.numOfArticles;
        return first.compareTo(sec);
    }
}
