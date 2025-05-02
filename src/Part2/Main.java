package Part2;

import java.util.Scanner;

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
        String lectId,lectName,degreeName;
        int salary;
        do {
            lectName = getStringFromUser("name");
            theCollege.isNameNotExist(lectName);
            if(!theCollege.isNameNotExist(lectName)) {
                System.out.println("lecturer name already exists");
            }
        }while (!theCollege.isNameNotExist(lectName));
        degreeName=getStringFromUser("degree name");
        do {
            salary = getIntFromUser("Salary");
            if (salary < 0) {
                System.out.println("Invalid salary try again!");
            }
        }while (salary<0);
        System.out.println("lecturer degree: Bachelor/Master/Phd/Professor");
        Lecturer.eDegree degree = Lecturer.eDegree.valueOf(s.nextLine());
        do{
            lectId=getStringFromUser("Id");
            if(lectId.length()!=9){
                System.out.println("Invalid id try again!");
            }
        }while (lectId.length()!=9);
        Lecturer lect1=new Lecturer(lectName,lectId,degreeName,salary,degree);
        if(theCollege.addLecturer(lect1)){
            System.out.println("lecturer added successfully");
        }
        else{
            System.out.println("failed adding lecturer");
        }
    }

    private static void addCommission(College theCollege) {
        String commissionName,HeadOfCommissionName,degreeName,lectId;
        int salary;
        commissionName=getStringFromUser("Commission name ");
        HeadOfCommissionName=getStringFromUser("Head Of Commission Name");
        if(!theCollege.isNameNotExist(HeadOfCommissionName)){
            Lecturer l1=theCollege.getLecturerByName(HeadOfCommissionName);
            Commission commission=new Commission(commissionName,l1);
            if(theCollege.addCommision(commission)){
                System.out.println("commission added successfully");
                return;
            }
            else{
                System.out.println("failed adding commission");
                return;
            }
        }
        degreeName=getStringFromUser("degree name");
        salary=getIntFromUser("Salary");
        System.out.println("lecturer degree: Bachelor/Master/Phd/Professor");
        Lecturer.eDegree degree = Lecturer.eDegree.valueOf(s.nextLine());
        do{
            lectId=getStringFromUser("Id");
            if(lectId.length()!=9){
                System.out.println("Invalid id try again");
            }
        }while (lectId.length()!=9);
        Lecturer headOfcommission= new Lecturer(HeadOfCommissionName,lectId,degreeName,salary,degree);
        Commission commission=new Commission(commissionName,headOfcommission);
        if(theCollege.addCommision(commission)){
            System.out.println("commission added successfully");
        }
        else{
            System.out.println("failed adding commission");
        }
    }

    private static void addMemberToCommission(College theCollege) {
        String commissionName,lectName;
        commissionName=getStringFromUser("Commission name: ");
        lectName=getStringFromUser("name");
        if(theCollege.addMemberToCommissionTeam(commissionName,lectName)){
            System.out.println("Lecturer added to commission team");
        }
        else{
            System.out.println("Lecturer or commission does not exist");
        }
    }

    private static void updateHeadOfCommission(College theCollege) {
        String commissionName,lectName;
        commissionName=getStringFromUser("Commission name: ");
        lectName=getStringFromUser("lecturer name");
        if(theCollege.isPossibleToChangeHeadOfCommission(commissionName,lectName)){
            System.out.println("Head of commission changed");
        }
        else{
            System.out.println("Update head of commission failed");
        }
    }

    private static void removeMemberFromCommission(College theCollege) {
        String commissionName,lectName;
        commissionName=getStringFromUser("Commission name: ");
        lectName=getStringFromUser("lecturer name");
        if(theCollege.removeMemberFromCommission(commissionName,lectName)){
            System.out.println("Member removed successfully");
        }
        else {
            System.out.println("Removing member failed");
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
                        "The average salary in the department is " + avg);
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
            System.out.println("Lecturer or department does not exist! ");
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
