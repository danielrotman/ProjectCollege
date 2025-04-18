package Part2;

public class Lecturer {
    private String name;
    private String id;
    public enum eDegree{Bachelor,Master,Phd,Professor};
    private eDegree degree;
    private String degreeName;
    private int salary;
    private Department lectDept;
    private Commission[]lectCommissions;

    public Lecturer(String name, String id, String degreeName, int salary,eDegree degree) {
        setName(name);
        setId(id);
        setDegreeName(degreeName);
        setSalary(salary);
        setDegree(degree);
    }
    public Lecturer(Lecturer l2){
        this(l2.name,l2.id, l2.degreeName, l2.salary, l2.degree);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public eDegree getDegree() {
        return degree;
    }

    public void setDegree(eDegree degree) {
        this.degree = degree;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getLectDept() {
        return lectDept;
    }

    public Commission[] getLectCommissions() {
        return lectCommissions;
    }
    @Override
    public String toString() {
        return "Lecturer{name='" + name + "', id='" + id + "', degree=" + degree + ", salary=" + salary +",degree name="+degreeName+ "}";
    }

}
