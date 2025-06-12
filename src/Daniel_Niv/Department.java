package Daniel_Niv;
import java.util.List;
import java.util.ArrayList;


public class Department implements Cloneable {
    private String departmentName;
    private int numOfStudents;
    private List <Lecturer>deptLectrures=new ArrayList<>();


    public Department(String departmentName, int numOfStudents) {
        setDepartmentName(departmentName);
        setNumOfStudents(numOfStudents);
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public boolean addToDepartmentTeam(Lecturer l1){
        for(int i=0;i<deptLectrures.size();i++) {
            if(deptLectrures.get(i) !=null&& deptLectrures.get(i) ==l1){
                return false;
            }
            if (deptLectrures.get(i) == null) {
                deptLectrures.set(i, l1);
            }
        }
        return true;


}

    @Override
    public Department clone() throws CloneNotSupportedException {
        Department copy = (Department) super.clone();

        // שכפול עמוק של רשימת המרצים
        copy.deptLectrures = new ArrayList<>();
        for (Lecturer l : this.deptLectrures) {
            copy.deptLectrures.add(l.clone());
        }

        return copy;
    }

    public int getNumOfDeptLecturers() {
        return deptLectrures.size();
    }
}
