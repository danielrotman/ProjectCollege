package Daniel_Niv;

import java.util.Scanner;

//Daniel Rotman id:324069657, Niv Markovich id:207532680

public class Main {
    private static final String[] MENU={
            "Press 0 to exit",
            "Press 1 to add lecturer",
            "Press 2 to add commission",
            "Press 3 to add lecturer to commission",
            "Press 4 to update the head of commission",
            "Press 5 to remove lecturer from commission",
            "Press 6 to add new department",
            "Press 7 to show average salaries of all lecturer's ",
            "Press 8 to show average salaries of specific department lecturer's ",
            "Press 9 to show the infos of all lecturer's ",
            "Press 10 to show the infos of all commission's ",
            "Press 11 to add lecturer to department",
            "Press 12 to compare between doctors/professors by articles",
            "Press 13 to compare between departments",
            "Press 14 to duplicate commission",
    };
    public static Scanner s;
    public static void main(String[]args){
        int userOption;
        s=new Scanner(System.in);
        String theCollege;
        System.out.println("Enter college name: ");
        theCollege=s.nextLine();
        College c1=new College(theCollege);
        do{
            userOption= Showmenu();
            switch (userOption){
                case 0->System.out.println("Done... Bye");
                case 1->addLecturer(c1);
                case 2->addCommission(c1);
                case 3-> addMemberToCommission(c1);
                case 4-> updateHeadOfCommission(c1);
                case 5-> removeMemberFromCommission(c1);
                case 6-> addDepartment(c1);
                case 7-> ShowLecturersAvgSalary(c1);
                case 8-> showDepartmentLecturersAvgSalary(c1);
                case 9-> ShowAllLecturersDetails(c1);
                case 10-> ShowCommissionsDetails(c1);
                case 11->addLecturerToDepartment(c1);
                default -> System.out.println("Invalid option choose again!");
            }
        }
        while (userOption!=0);
        s.close();
    }
    private static int Showmenu() {
        System.out.println("\n====== Menu =======");
        for (int i = 0; i < MENU.length; i++) {
            System.out.println(i + ". " + MENU[i]);
        }
        System.out.println("Enter your choose: ");
        return Integer.parseInt(s.nextLine());
    }

    private static void addLecturer(College theCollege) {
        String lectId, lectName = "", degreeName;
        boolean nameOk = false;
        boolean degreeOk = false;
        int salary;
        while (!nameOk) {
            try {
                lectName = getStringFromUser("name");
                theCollege.isNameNotExist(lectName);
                nameOk = true;
            } catch (AlreadyExistException e) {
                System.out.println(e.getMessage());
            }
        }
        degreeName = getStringFromUser("degree name");
        do {
            salary = getIntFromUser("Salary");
            if (salary < 0) {
                System.out.println("Invalid salary try again!");
            }
        } while (salary < 0);
        Lecturer.eDegree degree = null;
        while (!degreeOk) {
            try {
                System.out.println("lecturer degree: Bachelor/Master/Phd/Professor");
                degree = Lecturer.eDegree.valueOf(s.nextLine());
                degreeOk = true;
            } catch (IllegalArgumentException e) {
                System.out.println("not one of the options");
            }
        }
        do {
            lectId = getStringFromUser("Id");
            if (lectId.length() != 9) {
                System.out.println("Invalid id try again!");
            }
        } while (lectId.length() != 9);
        Lecturer lect1 = new Lecturer(lectName, lectId, degreeName, salary, degree);
        theCollege.addLecturer(lect1);
        System.out.println("lecturer added successfully");
    }

    private static void addCommission(College theCollege) {
        String commissionName = "", HeadOfCommissionName = "", degreeName, lectId;
        Lecturer.eDegree degree = null;
        int salary;
        boolean nameOk=false;
        while (!nameOk) {
            try {
                commissionName = getStringFromUser("Commission name");
                theCollege.isCommissionExist(commissionName);
                HeadOfCommissionName = getStringFromUser("Head Of Commission Name");
                theCollege.isNameNotExist(HeadOfCommissionName);
                System.out.println("lecturer degree: Bachelor/Master/Phd/Professor");
                degree = Lecturer.eDegree.valueOf(s.nextLine());
                degreeName = getStringFromUser("degree name");
                salary = getIntFromUser("Salary");
                do {
                    lectId = getStringFromUser("Id");
                    if (lectId.length() != 9) {
                        System.out.println("Invalid id try again");
                    }
                } while (lectId.length() != 9);
                Lecturer headOfcommission = new Lecturer(HeadOfCommissionName, lectId, degreeName, salary, degree);
                theCollege.addLecturer(headOfcommission);
                Commission commission = new Commission(commissionName, headOfcommission);
                theCollege.addCommision(commission);
                System.out.println("commission added successfully");
                nameOk = true;
            } catch (AlreadyExistException |NotQualifiedException e) {
                System.out.println(e.getMessage());
            }
             catch (Exception e){
                 System.out.println("General Error");
             }
        }

    }

    private static void addMemberToCommission(College theCollege) {
        String commissionName = "",lectName = "";
        boolean nameOk=false;
        while (!nameOk) {
            try {
                commissionName = getStringFromUser("Commission name");
                lectName = getStringFromUser("name");
                theCollege.IsNameExist(lectName);
                theCollege.addMemberToCommissionTeam(commissionName,lectName);
                nameOk = true;
                System.out.println("Lecturer added successfully");
            } catch (CommissionNotExistException | LecturerNotExistException | LectruerAlreadyHeadOfCommissionException e) {
                System.out.println(e.getMessage());
            }
            catch (CollegeException e){
                System.out.println("General error: Lecturer already in commission");
            }
        }
    }

    private static void updateHeadOfCommission(College theCollege) {
        String commissionName,lectName;
            try {
                lectName = getStringFromUser("lecturer name");
                commissionName = getStringFromUser("Commission name ");
                theCollege.isPossibleToChangeHeadOfCommission(commissionName, lectName);
                System.out.println("Head of commission changed");
            }
            catch (CommissionNotExistException | LecturerNotExistException |LectruerAlreadyHeadOfCommissionException| NotQualifiedException e){
                System.out.println(e.getMessage());
            }
            catch (CollegeException e){
                System.out.println("General error");
            }

    }

    private static void removeMemberFromCommission(College theCollege) {
        String commissionName,lectName;
        try {
            lectName=getStringFromUser("lecturer name");
            commissionName=getStringFromUser("Commission name ");
            theCollege.removeMemberFromCommission(commissionName,lectName);
                System.out.println("Member removed successfully");
            }
         catch (CommissionNotExistException |LecturerNotExistException e) {
             System.out.println(e.getMessage());
        }
        catch (CollegeException e){
            System.out.println("General Error");
        }
    }

    private static void addDepartment(College theCollege) {
        String departmentName;
        int numOfStudents;
        departmentName=getStringFromUser("department name");
        numOfStudents=getIntFromUser("num of students in the department");
        Department department=new Department(departmentName,numOfStudents);
        if(theCollege.addDepartment(department)){
            System.out.println("Department added successfully");
            System.out.println("There are " + theCollege.getNumOfDepts() + " departments");
        }
        else{
            System.out.println("Department already exist or the num of students invalid.");
        }

    }

    private static void ShowLecturersAvgSalary(College theCollege) {
        System.out.println("The average salary in the college is "+theCollege.getLecturersAvgSalary());
    }

    private static void showDepartmentLecturersAvgSalary(College c1) {
        String deptName;
        deptName=getStringFromUser("Department name ");
        float avg = c1.getDepartmentLecturersAvgSalary(deptName);
        System.out.println(
                avg == 0 ?
                        "The department does not exit or There are no lecturers in this department currently." :
                        "The average salary in the department is " +String.format("%.2f", avg));
    }

    private static void ShowAllLecturersDetails(College theCollege) {
        System.out.println(theCollege.getAllLecturersDetails());
    }

    private static void ShowCommissionsDetails(College c1) {
        System.out.println(c1.toString());
    }

    private static void addLecturerToDepartment(College c1) {
        String deptName,lectName;
        deptName=getStringFromUser("Department name ");
        lectName=getStringFromUser("Lecturer name ");
        if(c1.addMemberToDepartment(deptName,lectName)){
            System.out.println("Lecturer added to department successfully! ");
        }
        else{
            System.out.println("Lecturer or department does not exist!/the lecturer already in department ");
        }
    }

    private static int getIntFromUser(String type){
        System.out.println("Enter "+type+" :");
        return Integer.parseInt(s.nextLine());
    }

    private static String getStringFromUser(String type){
        System.out.println("Enter "+type+" :");
        return s.nextLine();
    }

}
