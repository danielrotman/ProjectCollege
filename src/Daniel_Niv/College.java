package Daniel_Niv;

import Daniel_Niv.Exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class College implements Serializable {
    private final String collegeName;
    private final List<Lecturer> allLectrures = new ArrayList<>();
    private final List<Commission> allCommissions = new ArrayList<>();
    private final List<Department> allDepts = new ArrayList<>();

    public College(String collegeName) {
        this.collegeName = collegeName;
    }

    public static College loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        return (College) in.readObject();
    }

    public void saveToFile(String fileName) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(this);
    }

    public boolean containsCommission(String commName) {
        for (Commission c : allCommissions) {
            if (c.getCommissionName().equals(commName)) {
                return true;
            }
        }
        return false;
    }

    public void isCommissionExist(String name) throws AlreadyExistException {
        if (containsCommission(name)) {
            throw new AlreadyExistException(name);
        }
    }

    public boolean containsLecturer(String lecturerName) {
        for (Lecturer l : allLectrures) {
            if (l.getName().equals(lecturerName)) {
                return true;
            }
        }
        return false;
    }

    public void isNameNotExist(String name) throws AlreadyExistException {
        if (containsLecturer(name)) {
            throw new AlreadyExistException(name);
        }
    }

    public void IsNameExist(String name) throws LecturerNotExistException {
        if (!containsLecturer(name)) {
            throw new LecturerNotExistException(name);
        }
    }

    public void add(Lecturer l1) {
        allLectrures.add(l1);
    }


    public Commission getCommissionByName(String name) throws CollegeException {
        for (Commission allCommission : allCommissions) {
            if (allCommission.getCommissionName().equals((name))) {
                return allCommission;
            }
        }
        throw new CommissionNotExistException(name);
    }

    public void addCommission(Commission c1) throws CollegeException {
        if (c1.getHeadOfCommission().getDegree() == Lecturer.eDegree.Bachelor || c1.getHeadOfCommission().getDegree() == Lecturer.eDegree.Master) {
            throw new NotQualifiedException(c1.getHeadOfCommission().getName());
        }
        if (containsCommission(c1.getCommissionName())) {
            throw new AlreadyExistException(c1.getCommissionName());
        }
        allCommissions.add(c1);
    }

    public void addDepartment(Department department) throws CollegeException {
        allDepts.add(department);
    }


    public String getAllLecturersDetails() {
        StringBuilder sb = new StringBuilder("Lecturers:\n");
        for (Lecturer allLectrure : allLectrures) {
            sb.append(allLectrure.toString()).append("\n");
        }
        return sb.toString();
    }

    public float getLecturersAvgSalary() throws NoLecturersInCollegeException {
        if (allLectrures.isEmpty()) {
            throw new NoLecturersInCollegeException();
        }
        float avg;
        float sum = 0;
        for (Lecturer allLecturers : allLectrures) {
            sum = sum + allLecturers.getSalary();
        }
        avg = sum / allLectrures.size();
        return avg;
    }

    public float getDepartmentLecturersAvgSalary(String deptName) throws CollegeException {
        for (Department allDept : allDepts) {
            if (allDept.getDepartmentName().equals(deptName)) {
                if (allDept.getNumOfDeptLecturers() == 0) {
                    throw new NoLecturersInDepartmentException(deptName);
                }
                float sum = 0;
                for (Lecturer allLectrure : allLectrures) {

                    if (allLectrure != null && allLectrure.getLectDept().getDepartmentName().equals(deptName)) {
                        sum = sum + allLectrure.getSalary();
                    }
                }
                return sum / allDept.getNumOfDeptLecturers();
            }
        }
        throw new DepartmentNotExistException(deptName);
    }

    public void removeMemberFromCommission(String commissionNameToCompare, String name) throws CollegeException {
        boolean isCommExist = false;
        boolean isLectExist = false;
        Lecturer l1 = null;
        Commission comm1 = null;
        for (Lecturer allLectrure : allLectrures) {
            if (allLectrure.getName().equals(name)) {
                isLectExist = true;
                l1 = allLectrure;
            }
        }
        if (!isLectExist) {
            throw new LecturerNotExistException(name);
        }
        for (Commission allCommission : allCommissions) {
            if (allCommission.getCommissionName().equals(commissionNameToCompare)) {
                isCommExist = true;
                comm1 = allCommission;
            }
        }
        if (!isCommExist) {
            throw new CommissionNotExistException(commissionNameToCompare);
        }
        comm1.remove(l1);
        l1.removeLecturerFromCommission(comm1);
    }

    public void addMemberToCommissionTeam(String commissionNameToCompare, String name) throws CollegeException {
        boolean isCommExist = false;
        Lecturer l1 = null;
        Commission comm1 = null;
        for (Lecturer allLectrure : allLectrures) {
            if (allLectrure.getName().equals(name)) {
                l1 = allLectrure;
            }
        }

        for (Commission allCommission : allCommissions) {
            if (allCommission.getCommissionName().equals(commissionNameToCompare)) {
                isCommExist = true;
                comm1 = allCommission;
                if (comm1.getHeadOfCommission().getName().equals(name)) {
                    throw new LectruerAlreadyHeadOfCommissionException(name);
                }
            }
        }
        if (!isCommExist) {
            throw new CommissionNotExistException(commissionNameToCompare);
        }
        comm1.IsLecturerNotInCommissionTeam(l1);
        comm1.add(l1);
        l1.lecturerCommissionsList(comm1);
    }

    public void addMemberToDepartment(String deptName, String name) throws CollegeException {
        boolean isDeptExist = false;
        boolean isLectExist = false;
        Lecturer l1 = null;
        Department dept1 = null;
        for (Lecturer allLectrure : allLectrures) {
            if (allLectrure.getName().equals(name)) {
                isLectExist = true;
                l1 = allLectrure;
            }
        }
        if (!isLectExist) {
            throw new LecturerNotExistException(name);
        }
        for (Department allDept : allDepts) {
            if (allDept.getDepartmentName().equals(deptName)) {
                isDeptExist = true;
                dept1 = allDept;
            }
        }
        if (!isDeptExist) {
            throw new DepartmentNotExistException(deptName);
        }
        if (l1.getLectDept() != null) {
            throw new LecturerAlreadyInDept(name);
        }
        l1.setLectDept(dept1);
        dept1.addToDepartmentTeam(l1);
    }

    public void isPossibleToChangeHeadOfCommission(String CommissionName, String name) throws CollegeException {
        boolean isCommExist = false;
        boolean isLectExist = false;
        Lecturer l1 = null;
        Commission comm1 = null;
        for (Lecturer allLectrure : allLectrures) {
            if (allLectrure.getName().equals(name)) {
                isLectExist = true;
                l1 = allLectrure;
            }
        }
        if (!isLectExist) {
            throw new LecturerNotExistException(name);
        }
        for (Commission allCommission : allCommissions) {
            if (allCommission.getCommissionName().equals(CommissionName)) {
                isCommExist = true;
                comm1 = allCommission;
                if (comm1.getHeadOfCommission().getName().equals(name)) {
                    throw new LectruerAlreadyHeadOfCommissionException(name);
                }
            }
        }
        if (!isCommExist) {
            throw new CommissionNotExistException(CommissionName);
        }
        if (l1.getDegree() == Lecturer.eDegree.Bachelor || l1.getDegree() == Lecturer.eDegree.Master) {
            throw new NotQualifiedException(name);
        }
        comm1.IsLecturerNotInCommissionTeam(l1);
        comm1.setHeadOfCommission(l1);
    }

    public int comparebymembers(Commission c1, Commission c2) {
        CompareNumOfMembers comparator = new CompareNumOfMembers();
        return comparator.compare(c1, c2);
    }

    public int comparebyarticles(Commission c1, Commission c2) {
        Comparebyarticles comparator = new Comparebyarticles();
        return comparator.compare(c1, c2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("College " + collegeName + " has " + allCommissions.size() + " commission's: ");
        for (Commission allCommission : allCommissions) {
            sb.append(allCommission.toString()).append("\n");
        }
        return sb.toString();
    }

    public int compare(String lectName1, String lectName2) throws CollegeException {
        Lecturer l1 = null;
        Lecturer l2 = null;
        for (Lecturer allLectrure : allLectrures) {
            if (allLectrure.getName().equals(lectName1)) {
                l1 = allLectrure;
            } else if (allLectrure.getName().equals(lectName2)) {
                l2 = allLectrure;
            }
        }
        if (l1 instanceof Phd && l2 instanceof Phd) {
            return ((Phd) l1).compareTo((Phd) (l2));
        }
        throw new CannotComapreException();
    }

    public void dupComm(Commission commission) throws CloneNotSupportedException, CollegeException {
        Commission dup = commission.clone();
        dup.setCommissionName(dup.getCommissionName() + "-new");
        addCommission(dup);
    }
}
