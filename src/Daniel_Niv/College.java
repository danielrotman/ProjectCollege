package Daniel_Niv;

import java.util.Arrays;

public class College {
    private Lecturer[] allLectrures;
    private int numOfLectrures;
    private Commission[]allCommissions;
    private int numOfCommissions;
    private Department[]allDepts;
    private int numOfDepts;
    private String collegeName;

    public College(int numOfLectrures, int numOfCommissions, int numOfDepts, String collegeName) {
        this.numOfLectrures = numOfLectrures;
        this.numOfCommissions = numOfCommissions;
        this.numOfDepts = numOfDepts;
        this.collegeName = collegeName;
    }

    public College(String collegeName) {
        this(0,0,0,collegeName);
    }

    public void isCommissionExist(String name)throws AlreadyExistException {
        for (int i = 0; i < numOfCommissions; i++) {
            if (allCommissions[i].getCommissionName().equals((name))) {
                throw new AlreadyExistException(name);
            }
        }
    }

    public void isNameNotExist(String name) throws AlreadyExistException {
        for (int i = 0; i < numOfLectrures; i++) {
            if (allLectrures[i].getName().equals((name))) {
                throw new AlreadyExistException(name);
            }
        }
    }
    public void IsNameExist(String name) throws CollegeException {
        boolean isHere=false;
        for (int i = 0; i < numOfLectrures; i++) {
            if (allLectrures[i].getName().equals((name))) {
               isHere=true;
            }
        }
        if(!isHere){
            throw new LecturerNotExistException(name);
    }
    }

    public void addLecturer(Lecturer l1)  {
        if(numOfLectrures==0){
            allLectrures=new Lecturer[2];
            allLectrures[numOfLectrures++]=l1;
        }
        else {
            if (numOfLectrures == allLectrures.length) {
                allLectrures = Arrays.copyOf(allLectrures, allLectrures.length * 2);
            }
            allLectrures[numOfLectrures++] = l1;
        }
    }

    public Lecturer getLecturerByName(String name){
        for (int i = 0; i < numOfLectrures; i++) {
            if (allLectrures[i].getName().equals((name))) {
                return allLectrures[i];
            }
        }
        return null;
    }

    public void addCommision(Commission commission) throws CollegeException{
        if (commission.getHeadOfCommission().getDegree()== Lecturer.eDegree.Bachelor || commission.getHeadOfCommission().getDegree()==Lecturer.eDegree.Master) {
            throw new NotQualifiedException(commission.getHeadOfCommission().getName());
        }
            for(int i=0;i<numOfCommissions;i++) {
                if (allCommissions[i].getCommissionName().equals(commission.getCommissionName())) {
                    throw new AlreadyExistException(commission.getCommissionName());
                }
            }
                if(allCommissions==null || numOfCommissions==allCommissions.length){
                    allCommissions=(allCommissions==null)?new Commission[2]:Arrays.copyOf(allCommissions,allCommissions.length*2);
                }
                allCommissions[numOfCommissions++]=commission;
            }

    public boolean addDepartment(Department department) {
        if (department.getNumOfStudents() < 0) {
            return false;
        } else {
            for (int i = 0; i < numOfDepts; i++) {
                if (allDepts[i].getDepartmentName().equals(department.getDepartmentName())) {
                    return false;
                }
            }
            if (allDepts == null || numOfDepts == allDepts.length) {
                allDepts = (allDepts == null) ? new Department[2] : Arrays.copyOf(allDepts, allDepts.length * 2);
            }
            allDepts[numOfDepts++] = department;
            return true;
        }
    }

    public int getNumOfDepts() {
        return numOfDepts;
    }

    public String getAllLecturersDetails() {
        StringBuilder sb = new StringBuilder("Lecturers:\n");
        for (int i = 0; i < numOfLectrures; i++) {
            sb.append(allLectrures[i].toString()).append("\n");
        }
        return sb.toString();
    }

    public float getLecturersAvgSalary(){
        float avg;
        float sum=0;
        for(int i=0;i<numOfLectrures;i++){
            sum=sum+allLectrures[i].getSalary();
        }
        avg=sum/numOfLectrures;
        return avg;
    }

    public float getDepartmentLecturersAvgSalary(String deptName) {
        for (int i = 0; i < numOfDepts; i++) {
            if (allDepts[i].getDepartmentName().equals(deptName)) {
                Department dept = allDepts[i];
                if (dept.getNumOfDeptLecturers() == 0) {
                    return 0;
                }
                float sum = 0;
                for (int j = 0; j < numOfLectrures;j++) {

                    if(allLectrures[j]!=null&&allLectrures[j].getLectDept().getDepartmentName().equals(deptName)) {
                        sum = sum + allLectrures[j].getSalary();
                    }
                }
                return sum/dept.getNumOfDeptLecturers();
            }
        }
        return 0;
    }

    public void removeMemberFromCommission(String commissionNameToCompare, String name)throws CollegeException{
        boolean isCommExist=false;
        boolean isLectExist=false;
        Lecturer l1 = null;
        Commission comm1 = null;
        for(int i=0;i<numOfLectrures;i++){
            if(allLectrures[i].getName().equals(name)){
                isLectExist=true;
                l1=allLectrures[i];
            }
        }
        if(!isLectExist){
            throw new LecturerNotExistException(name);
        }
        for(int j=0;j<numOfCommissions;j++){
            if(allCommissions[j].getCommissionName().equals(commissionNameToCompare)){
                isCommExist=true;
                comm1=allCommissions[j];
            }
        }
        if(!isCommExist) {
            throw new CommissionNotExistException(commissionNameToCompare);
        }
            comm1.removeLectFromCommission(l1);
            l1.removeLecturerFromCommission(comm1);
    }

    public void addMemberToCommissionTeam(String commissionNameToCompare, String name)throws CollegeException{
        boolean isCommExist=false;
        Lecturer l1 = null;
        Commission comm1 = null;
        for(int i=0;i<numOfLectrures;i++){
            if(allLectrures[i].getName().equals(name)){
                l1=allLectrures[i];
            }
        }

        for(int j=0;j<numOfCommissions;j++){
            if(allCommissions[j].getCommissionName().equals(commissionNameToCompare)){
                isCommExist=true;
                comm1=allCommissions[j];
                if (comm1.getHeadOfCommission().getName().equals(name)){
                    throw new LectruerAlreadyHeadOfCommissionException(name);
                }
            }
        }
        if(!isCommExist){
            throw new CommissionNotExistException(commissionNameToCompare);
        }
            comm1.IsLecturerNotInCommissionTeam(l1);
                comm1.addToCommissionTeam(l1);
                l1.lecturerCommissionsList(comm1);
    }

    public boolean addMemberToDepartment(String deptName,String name){
        boolean isDeptExist=false;
        boolean isLectExist=false;
        boolean isPossibleToAddLect=false;
        Lecturer l1 = null;
        Department dept1 = null;
        for(int i=0;i<numOfLectrures;i++){
            if(allLectrures[i].getName().equals(name)){
                isLectExist=true;

                l1=allLectrures[i];
            }
        }
        for(int j=0;j<numOfDepts;j++){
            if(allDepts[j].getDepartmentName().equals(deptName)){
                isDeptExist=true;

                dept1=allDepts[j];
            }
        }

        if(isDeptExist && isLectExist) {
            if (l1.getLectDept() == null) {
                isPossibleToAddLect = true;
                l1.setLectDept(dept1);
                return dept1.addToDepartmentTeam(l1);
            }
        }
            return false;




    }

    public void isPossibleToChangeHeadOfCommission(String CommissionName,String name) throws CollegeException{
        boolean isCommExist=false;
        boolean isLectExist=false;
        Lecturer l1 = null;
        Commission comm1 = null;
        for(int i=0;i<numOfLectrures;i++){
            if(allLectrures[i].getName().equals(name)){
                isLectExist=true;
                l1=allLectrures[i];
            }
        }
        if (!isLectExist){
            throw new LecturerNotExistException(name);
        }
        for(int j=0;j<numOfCommissions;j++){
            if(allCommissions[j].getCommissionName().equals(CommissionName)){
                isCommExist=true;
                comm1=allCommissions[j];
                if (comm1.getHeadOfCommission().getName().equals(name)){
                    throw new LectruerAlreadyHeadOfCommissionException(name);
                }
            }
        }
        if (!isCommExist){
            throw new CommissionNotExistException(CommissionName);
        }
            if (l1.getDegree() == Lecturer.eDegree.Bachelor || l1.getDegree() == Lecturer.eDegree.Master) {
                throw new NotQualifiedException(name);
            }
            comm1.IsLecturerNotInCommissionTeam(l1);
            comm1.setHeadOfCommission(l1);
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder("College "+collegeName+" has "+numOfCommissions+" commission's: ");
        for(int i=0;i<numOfCommissions;i++){
            sb.append(allCommissions[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
