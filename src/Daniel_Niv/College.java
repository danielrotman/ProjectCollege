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
                isHere = true;
                break;
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

    public Commission getCommissionByName(String name)throws CollegeException{
        for (int i = 0; i < numOfCommissions; i++) {
            if (allCommissions[i].getCommissionName().equals((name))) {
                return allCommissions[i];
            }
        }
        throw new CommissionNotExistException(name);
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

    public void addDepartment(Department department) throws CollegeException{
        if (department.getNumOfStudents() < 0) {
            throw new InvalidNumException();
        } else {
            for (int i = 0; i < numOfDepts; i++) {
                if (allDepts[i].getDepartmentName().equals(department.getDepartmentName())) {
                    throw new AlreadyExistException(department.getDepartmentName());
                }
            }
            if (allDepts == null || numOfDepts == allDepts.length) {
                allDepts = (allDepts == null) ? new Department[2] : Arrays.copyOf(allDepts, allDepts.length * 2);
            }
            allDepts[numOfDepts++] = department;
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

    public void addMemberToDepartment(String deptName,String name) throws CollegeException{
        boolean isDeptExist=false;
        boolean isLectExist=false;
        Lecturer l1 = null;
        Department dept1 = null;
        for(int i=0;i<numOfLectrures;i++){
            if(allLectrures[i].getName().equals(name)){
                isLectExist=true;
                l1=allLectrures[i];
            }
        }
        if(!isLectExist){
            throw new LecturerNotExistException(name);
        }
        for(int j=0;j<numOfDepts;j++){
            if(allDepts[j].getDepartmentName().equals(deptName)){
                isDeptExist=true;
                dept1=allDepts[j];
            }
        }
        if(!isDeptExist){
            throw new DepartmentNotExistException(deptName);
        }
            if (l1.getLectDept() != null) {
                throw new LecturerAlreadyInDept(name);
            }
        l1.setLectDept(dept1);
        dept1.addToDepartmentTeam(l1);
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

    public int comparebymembers(Commission c1,Commission c2){
        CompareNumOfMembers comparator=new CompareNumOfMembers();
        int res=comparator.compare(c1,c2);
        return res;
    }

    public int comparebyarticles(Commission c1,Commission c2){
        Comparebyarticles comparator=new Comparebyarticles();
        int res=comparator.compare(c1,c2);
        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder("College "+collegeName+" has "+numOfCommissions+" commission's: ");
        for(int i=0;i<numOfCommissions;i++){
            sb.append(allCommissions[i].toString()).append("\n");
        }
        return sb.toString();
    }

    public int compare(String lectName1, String lectName2)throws CollegeException{
        Lecturer l1=null;
        Lecturer l2=null;
        for(int i=0;i<numOfLectrures;i++){
            if(allLectrures[i].getName().equals(lectName1)){
                 l1=allLectrures[i];
            } else if (allLectrures[i].getName().equals(lectName2)) {
                 l2=allLectrures[i];
            }
        }
            if(l1 instanceof Phd && l2 instanceof Phd) {
                int res = ((Phd) l1).compareTo((Phd) (l2));
                return res;
            }
                throw new CannotComapreException();
    }
}
