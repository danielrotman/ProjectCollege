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

    private static void addLecturerToDepartment(College c1) {
        String deptName,lectId;
        s.nextLine();
        System.out.println("Enter Department name: ");
        deptName=s.nextLine();
        System.out.println("Enter lecturer id");
        lectId=s.nextLine();
        if(c1.addMemberToDepartment(deptName,lectId)){
            System.out.println("Lecturer added to department");
        }
        else{
            System.out.println("Lecturer or department does not exist");
        }
    }

    private static void addDepartment(College theCollege) {
        Department department=createDepartment();
        if(theCollege.addDepartment(department)){
            System.out.println("Department added successfully");
            System.out.println("There are " + theCollege.getNumOfDepts() + " departments");
        }
        else{
            System.out.println("Department already exist or the num of students invalid.");
        }

    }

    private static void ShowCommissionsDetails(College c1) {
    System.out.println(c1.toString());
    }

    private static int Showmenu() {
        System.out.println("\n====== Menu =======");
        for (int i = 0; i < MENU.length; i++) {
            System.out.println(i + ". " + MENU[i]);
        }
        System.out.println("Enter your choose: ");
        return s.nextInt();
    }


    private static void addLecturer(College theCollege) {
        Lecturer lecturer=createLecturer();
        if(theCollege.addLecturer(lecturer)){
            System.out.println("added successfully");
        }
        else{
            System.out.println("failed adding");
        }
    }

    private static void addCommission(College theCollege) {
        String commissionName;
        s.nextLine();
        System.out.println("Enter Commission name: ");
        commissionName=s.nextLine();
        Lecturer headOfcommision=createLecturer();
        Commission commission=new Commission(commissionName,headOfcommision);
        if(theCollege.addCommision(commission)){
            System.out.println("commission added successfully");
        }
        else{
            System.out.println("failed adding commission");
        }
    }

    private static void ShowAllLecturersDetails(College theCollege) {
        System.out.println(theCollege.getAllLecturersDetails());
    }

    private static void showDepartmentLecturersAvgSalary(College c1) {
        String deptName;
        s.nextLine();
        System.out.println("Enter Department name: ");
        deptName=s.nextLine();
        float avg = c1.getDepartmentLecturersAvgSalary(deptName);
        System.out.println(
                avg == 0 ?
                        "The department does not exit or There are no lecturers in this department currently." :
                        "The average salary in the department is " + avg);
    }

    private static void ShowLecturersAvgSalary(College theCollege) {
        System.out.println("The average salary in the college is "+theCollege.getLecturersAvgSalary());
    }

    private static void removeMemberFromCommission(College theCollege) {
        String commissionName,lectId;
        s.nextLine();
        System.out.println("Enter Commission name: ");
        commissionName=s.nextLine();
        System.out.println("Enter lecturer id");
        lectId=s.nextLine();
        if(theCollege.removeMemberFromCommission(commissionName,lectId)){
            System.out.println("Member removed successfully");
        }
        else {
            System.out.println("Removing member failed");
        }
    }

    private static void updateHeadOfCommission(College theCollege) {
        String commissionName,lectId;
        s.nextLine();
        System.out.println("Enter Commission name: ");
        commissionName=s.nextLine();
        System.out.println("Enter lecturer id");
        lectId=s.nextLine();
        if(theCollege.isPossibleToChangeHeadOfCommission(commissionName,lectId)){
            System.out.println("Head of commission changed");
        }
        else{
            System.out.println("Update head of commission failed");
        }
    }

    private static void addMemberToCommission(College theCollege) {
        String commissionName,lectId;
        s.nextLine();
        System.out.println("Enter Commission name: ");
        commissionName=s.nextLine();
        System.out.println("Enter lecturer id");
        lectId=s.nextLine();
        if(theCollege.addMemberToCommissionTeam(commissionName,lectId)){
            System.out.println("Lecturer added to commission team");
        }
        else{
            System.out.println("Lecturer or commission does not exist");
        }
    }

    private static Lecturer createLecturer(){
        String lectId,lectName,degreeName;
        int salary;
        System.out.println("Enter lecturer degree: Bachelor/Master/Phd/Professor");
        Lecturer.eDegree degree=Lecturer.eDegree.valueOf(s.next());
        System.out.println("Enter lecturer salary");
        salary=s.nextInt();
        s.nextLine();
        System.out.println("Enter lecturer degree name");
        degreeName=s.nextLine();
        System.out.println("Enter lecturer name");
        lectName=s.nextLine();
        do {
            System.out.println("Enter lecturer id");
            lectId = s.nextLine();
            if(lectId.length()!=9){
                System.out.println("Invalid id try again");
            }
        }
        while(lectId.length()!=9);
        Lecturer lect1=new Lecturer(lectName,lectId,degreeName,salary,degree);
        return lect1;
    }
    private static Department createDepartment(){
        String departmentName;
        int numOfStudents;
        s.nextLine();
        System.out.println("Enter the department name: ");
        departmentName=s.nextLine();
        System.out.println("Enter how many students in the department");
        numOfStudents=s.nextInt();
        Department department=new Department(departmentName,numOfStudents);
        return department;
    }


}
