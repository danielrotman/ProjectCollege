package Part2;

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




}
