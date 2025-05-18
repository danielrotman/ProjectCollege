package Daniel_Niv;

public class Professor extends Phd{
    private String certification;
    public Professor(String name, String id, String degreeName, int salary, eDegree degree,int numOfArticles,String certification) {
        super(name, id, degreeName, salary, degree,numOfArticles);
        this.certification=certification;

    }

    public String getcertification() {
        return certification;
    }

}
