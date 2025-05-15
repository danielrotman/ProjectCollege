package Daniel_Niv;

public class Professor extends Lecturer{
    int numOfArticles;
    public Professor(String name, String id, String degreeName, int salary, eDegree degree) {
        super(name, id, degreeName, salary, degree);
        setNumOfArticles(numOfArticles);
    }

    public int getNumOfArticles() {
        return numOfArticles;
    }

    public void setNumOfArticles(int numOfArticles) {
        this.numOfArticles = numOfArticles;
    }
}
