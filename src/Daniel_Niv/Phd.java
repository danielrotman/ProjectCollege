package Daniel_Niv;

public class Phd extends Lecturer{
    protected int numOfArticles;

    public Phd(String name, String id, String degreeName, int salary, eDegree degree,int numOfArticles) {
        super(name, id, degreeName, salary, degree);
        this.numOfArticles=numOfArticles;
    }

    public int getNumOfArticles() {
        return numOfArticles;
    }

}
