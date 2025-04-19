package Part2;

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

    public void addToDepartmentTeam(Lecturer l1){
            if(numOfDeptLecturers==0){
        deptLectrures=new Lecturer[2];
        deptLectrures[numOfDeptLecturers++]=l1;
    }
    else {
        for(int i=0;i<deptLectrures.length;i++) {
            if (deptLectrures[i] == null) {
                deptLectrures[i] = l1;
                numOfDeptLecturers++;
                return;
            }
        }
        deptLectrures = Arrays.copyOf(deptLectrures, deptLectrures.length * 2);
        deptLectrures[numOfDeptLecturers++] = l1;

    }
}

    public int getNumOfDeptLecturers() {
        return numOfDeptLecturers;
    }
}
