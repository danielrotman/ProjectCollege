package Daniel_Niv;

import java.util.Arrays;

public class Department {
    private String departmentName;
    private int numOfStudents;
    private Lecturer []deptLectrures;
    private int numOfDeptLecturers;


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
            if(numOfDeptLecturers==0){
        deptLectrures=new Lecturer[2];
        deptLectrures[numOfDeptLecturers++]=l1;
        return true;
    }
    else {
        for(int i=0;i<deptLectrures.length;i++) {
            if(deptLectrures[i]!=null&&deptLectrures[i]==l1){
                return false;
            }
            if (deptLectrures[i] == null) {
                deptLectrures[i] = l1;
                numOfDeptLecturers++;
            }
        }
        deptLectrures = Arrays.copyOf(deptLectrures, deptLectrures.length * 2);
        deptLectrures[numOfDeptLecturers++] = l1;
        return true;

    }
}

    public int getNumOfDeptLecturers() {
        return numOfDeptLecturers;
    }
}
