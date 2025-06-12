package Daniel_Niv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lecturer implements Cloneable {
    private String name;
    private String id;
    public enum eDegree{Bachelor,Master,Phd,Professor};
    private eDegree degree;
    private String degreeName;
    private int salary;
    private Department lectDept;
    private List <Commission> lectCommissions= new ArrayList<>();

    public Lecturer(String name, String id, String degreeName, int salary,eDegree degree) {
        setName(name);
        setId(id);
        setDegreeName(degreeName);
        setSalary(salary);
        setDegree(degree);
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

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void removeLecturerFromCommission(Commission commission){
        if(lectCommissions.isEmpty()){
            return;
        }
        for(int i=0;i<lectCommissions.size();i++){
            if(lectCommissions.get(i) ==commission){
                lectCommissions.set(i, null);
            }
        }
    }

    public Department getLectDept() {
        return lectDept;
    }
    public void setLectDept(Department lectDept) {
        this.lectDept = lectDept;
    }
    public void lecturerCommissionsList(Commission c1){
     lectCommissions.add(c1);
    }

    @Override
    protected Lecturer clone() throws CloneNotSupportedException {
        Lecturer copy=(Lecturer) super.clone();
        if(this.lectDept!=null) {
            copy.lectDept = lectDept.clone();
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("Lecturer name: ").append(name).append('\n').append("Commission member in: [");
        if(lectCommissions.isEmpty()){
            sb.append("The lecturer is not a member of any commission");
        }
        else{
            boolean first=true;
            for(Commission comm:lectCommissions){
                if(comm!=null){
                    if(!first) {
                        sb.append(",");
                    }
                    sb.append(comm.getCommissionName());
                    first=false;
                }
            }
        }
        sb.append("]").append('\n');
        sb.append("id= ").append(id).append(", degree type: ").append(degree).append(", Salary: ").append(salary).append(", Degree name: ").append(degreeName);
        return sb.toString();
    }

}
