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

    public boolean addLecturer(Lecturer l1){
        if(numOfLectrures==0){
            allLectrures=new Lecturer[2];
            allLectrures[numOfLectrures++]=l1;
        }
        else {
            for (int i = 0; i < numOfLectrures; i++) {
                if (allLectrures[i].getId().equals(l1.getId())) {
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

    public boolean addDepartment(Department department){
        if(numOfDepts==0){
            allDepts=new Department[2];
            if(department.getNumOfStudents()<0){
                return false;
            }
            allDepts[numOfDepts++]=department;

        }
        else {
            for (int i = 0; i < numOfDepts; i++) {
                if (allDepts[i].getDepartmentName().equals(department.getDepartmentName())) {
                    return false;
                }
                if (department.getNumOfStudents() < 0) {
                    return false;
                }
            }
            if(numOfDepts==allDepts.length){
                allDepts = Arrays.copyOf(allDepts, allDepts.length * 2);
            }
            allDepts[numOfDepts++]=department;
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder("College "+collegeName+" has"+numOfCommissions+" ");
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
    public boolean removeMemberFromCommission(String commissionNameToCompare, String id){
        boolean isCommExist=false;
        boolean isLectExist=false;
        Lecturer l1 = null;
        Commission comm1 = null;
        for(int i=0;i<numOfLectrures;i++){
            if(allLectrures[i].getId().equals(id)){
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
    public boolean addMemberToCommissionTeam(String commissionNameToCompare, String id){
        boolean isCommExist=false;
        boolean isLectExist=false;
        Lecturer l1 = null;
        Commission comm1 = null;
        for(int i=0;i<numOfLectrures;i++){
            if(allLectrures[i].getId().equals(id)){
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
            return true;
        }
        else {
            return false;
        }


    }
    public boolean isPossibleToChangeHeadOfCommission(String CommissionName,String id){
        boolean isCommExist=false;
        boolean isLectExist=false;
        boolean isLectQualified=true;
        Lecturer l1 = null;
        Commission comm1 = null;
        for(int i=0;i<numOfLectrures;i++){
            if(allLectrures[i].getId().equals(id)){
                isLectExist=true;
                l1=allLectrures[i];
            }
        }
        for(int j=0;j<numOfCommissions;j++){
            if(allCommissions[j].getCommissionName().equals(CommissionName)){
                isCommExist=true;
                comm1=allCommissions[j];
            }
        }
        if(comm1.getHeadOfCommission().getDegree()== Lecturer.eDegree.Bachelor || comm1.getHeadOfCommission().getDegree()==Lecturer.eDegree.Master){
            isLectQualified=false;
        }
        if(isCommExist&&isLectExist&&isLectQualified){
            comm1.setHeadOfCommission(l1);
            return true;
        }
        else{
            return false;
        }
    }

}
