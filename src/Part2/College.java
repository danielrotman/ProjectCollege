package Part2;

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
    public boolean isNameNotExist(String name) {
        for (int i = 0; i < numOfLectrures; i++) {
            if (allLectrures[i].getName().equals((name))) {
                return false;
            }
        }
        return true;
    }
    public boolean addLecturer(Lecturer l1){
        if(numOfLectrures==0){
            allLectrures=new Lecturer[2];
            allLectrures[numOfLectrures++]=l1;
        }
        else {
            for (int i = 0; i < numOfLectrures; i++) {
                if(allLectrures[i].getName().equals(l1.getName())){
                    return false;
                }
            }
            if (numOfLectrures == allLectrures.length) {
                allLectrures = Arrays.copyOf(allLectrures, allLectrures.length * 2);
            }
            allLectrures[numOfLectrures++] = l1;
        }
        return true;
    }
    public Lecturer getLecturerByName(String name){
        for (int i = 0; i < numOfLectrures; i++) {
            if (allLectrures[i].getName().equals((name))) {
                return allLectrures[i];
            }
        }
        return null;
    }
    public boolean addCommision(Commission commission){
        if (commission.getHeadOfCommission().getDegree()== Lecturer.eDegree.Bachelor || commission.getHeadOfCommission().getDegree()==Lecturer.eDegree.Master) {
            return false;
        }
            for(int i=0;i<numOfCommissions;i++) {
                if (allCommissions[i].getCommissionName().equals(commission.getCommissionName())) {
                    return false;
                }
            }
                if(allCommissions==null || numOfCommissions==allCommissions.length){
                    allCommissions=(allCommissions==null)?new Commission[2]:Arrays.copyOf(allCommissions,allCommissions.length*2);
                }
                allCommissions[numOfCommissions++]=commission;
                return true;
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

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder("College "+collegeName+" has "+numOfCommissions+" commission's: ");
        for(int i=0;i<numOfCommissions;i++){
           sb.append(allCommissions[i].toString()).append("\n");
        }
        return sb.toString();
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
                    if(allLectrures[j].getLectDept().getDepartmentName().equals(deptName)) {
                        sum = sum + allLectrures[j].getSalary();
                    }
                }
                return sum/dept.getNumOfDeptLecturers();
            }
        }
        return 0;
    }

    public boolean removeMemberFromCommission(String commissionNameToCompare, String name){
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
        for(int j=0;j<numOfCommissions;j++){
            if(allCommissions[j].getCommissionName().equals(commissionNameToCompare)){
                isCommExist=true;
                comm1=allCommissions[j];
            }
        }
        if(isCommExist&&isLectExist){
            comm1.removeLectFromCommission(l1);
            return true;
        }
        else {
            return false;
        }
    }
    public boolean addMemberToCommissionTeam(String commissionNameToCompare, String name){
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
        for(int j=0;j<numOfCommissions;j++){
            if(allCommissions[j].getCommissionName().equals(commissionNameToCompare)){
                isCommExist=true;
                comm1=allCommissions[j];
            }
        }
        if(isCommExist && isLectExist){
            comm1.addToCommissionTeam(l1);
            l1.lecturerCommissionsList(comm1);
            return true;
        }
        else {
            return false;
        }


    }
    public boolean addMemberToDepartment(String deptName,String name){
        boolean isDeptExist=false;
        boolean isLectExist=false;
        boolean isPossibleToAddLect=false;
        Lecturer l1 = null;
        Department dept1 = null;
        for(int i=0;i<numOfDepts;i++){
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
            }
        }
            if(isPossibleToAddLect) {
                dept1.addToDepartmentTeam(l1);
                return true;
            }
        else {
            return false;
        }


    }
    public boolean isPossibleToChangeHeadOfCommission(String CommissionName,String name){
        boolean isCommExist=false;
        boolean isLectExist=false;
        boolean isLectQualified=true;
        boolean isLectNotInCommssion=true;
        Lecturer l1 = null;
        Commission comm1 = null;
        for(int i=0;i<numOfLectrures;i++){
            if(allLectrures[i].getName().equals(name)){
                isLectExist=true;
                l1=allLectrures[i];
            }
        }
        for(int j=0;j<numOfCommissions;j++){
            if(allCommissions[j].getCommissionName().equals(CommissionName)){
                isCommExist=true;
                comm1=allCommissions[j];
            }
        }if(isCommExist && isLectExist) {
            if (comm1.getHeadOfCommission().getDegree() == Lecturer.eDegree.Bachelor || comm1.getHeadOfCommission().getDegree() == Lecturer.eDegree.Master) {
                isLectQualified = false;
            }
            isLectNotInCommssion = (comm1.IsLecturerNotInCommissionTeam(l1));
        }
        if(isCommExist&&isLectExist&&isLectQualified&&isLectNotInCommssion){
            comm1.setHeadOfCommission(l1);
            return true;
        }
        else{
            return false;
        }
    }

}
