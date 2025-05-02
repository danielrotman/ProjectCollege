package Part2;

import java.util.Arrays;

public class Lecturer {
    private String name;
    private String id;
    public enum eDegree{Bachelor,Master,Phd,Professor};
    private eDegree degree;
    private String degreeName;
    private int salary;
    private Department lectDept;
    private Commission[]lectCommissions;
    private int numOfLecturerCommissions;

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

    public Department getLectDept() {
        return lectDept;
    }
    public void setLectDept(Department lectDept) {
        this.lectDept = lectDept;
    }
    public void lecturerCommissionsList(Commission c1){
     if(numOfLecturerCommissions==0){
         lectCommissions=new Commission[2];
         lectCommissions[numOfLecturerCommissions]=c1;
     }
     else{
         for(int i=0;i<lectCommissions.length;i++){
         if(lectCommissions[i]==null){
             lectCommissions[i]=c1;
             numOfLecturerCommissions++;
             return;
         }
         }
     }
        lectCommissions= Arrays.copyOf(lectCommissions,lectCommissions.length*2);
        lectCommissions[numOfLecturerCommissions++]=c1;
    }
    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("Lecturer name: ").append(name).append('\n').append("Commission member in: [");
        if(numOfLecturerCommissions==0){
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
