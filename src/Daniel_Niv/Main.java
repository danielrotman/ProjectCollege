package Daniel_Niv;

import Daniel_Niv.Exceptions.*;

import java.io.IOException;
import java.util.Scanner;

//Daniel Rotman id:324069657, Niv Markovich id:207532680

public class Main {
    private static final String[] MENU = {
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

    public static void main(String[] args) {
        int userOption;
        s = new Scanner(System.in);
        final String FILE_NAME = "college_data.bin";
        College c1 ;
        try {
            c1 = College.loadFromFile(FILE_NAME);
            if (c1 == null) {
                System.out.println("Enter college name: ");
                String theCollege = s.nextLine();
                c1 = new College(theCollege);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Starting fresh file.");
            System.out.print("Enter college name: ");
            String theCollege = s.nextLine();
            c1 = new College(theCollege);
        }
        do {
            userOption = Showmenu();
            switch (userOption) {
                case 0 -> System.out.println("Done... Bye");
                case 1 -> addLecturer(c1);
                case 2 -> addCommission(c1);
                case 3 -> addMemberToCommission(c1);
                case 4 -> updateHeadOfCommission(c1);
                case 5 -> removeMemberFromCommission(c1);
                case 6 -> addDepartment(c1);
                case 7 -> ShowLecturersAvgSalary(c1);
                case 8 -> showDepartmentLecturersAvgSalary(c1);
                case 9 -> ShowAllLecturersDetails(c1);
                case 10 -> ShowCommissionsDetails(c1);
                case 11 -> addLecturerToDepartment(c1);
                case 12 -> CompareBetweenLecturers(c1);
                case 13 -> CompareBetweenCommissions(c1);
                case 14 -> DuplicateCommission(c1);
                default -> System.out.println("Invalid option choose again!");
            }
        }
        while (userOption != 0);
        try {
            c1.saveToFile(FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
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
        theCollege.add(createLectruer(theCollege));
    }

    private static void addCommission(College theCollege) {
        Lecturer lect1=null;
        String commissionName, HeadOfCommissionName, degreeName, lectId;
        Lecturer.eDegree degree = null;
        int salary;
        boolean nameOk = false;
        while (!nameOk) {
            try {
                commissionName = getStringFromUser("Commission name");
                theCollege.isCommissionExist(commissionName);
                HeadOfCommissionName = getStringFromUser("Head Of Commission Name");
                theCollege.isNameNotExist(HeadOfCommissionName);
                degreeName = getStringFromUser("degree name");
                salary = getIntFromUser("Salary");
                do {
                    lectId = getStringFromUser("Id");
                    if (lectId.length() != 9) {
                        System.out.println("Invalid id try again");
                    }
                } while (lectId.length() != 9);
                boolean degreeOk=false;
                while(!degreeOk) {
                    try {
                        System.out.println("lecturer degree: Bachelor/Master/Phd/Professor");
                        degree = Lecturer.eDegree.valueOf(s.nextLine());
                        degreeOk = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("not one of the options");
                    }
                }
                switch (degree.ordinal()) {
                    case 0, 1 -> {
                        lect1 = new Lecturer(HeadOfCommissionName, lectId, degreeName, salary, degree);
                        System.out.println("lecturer added successfully");
                        nameOk = true;
                    }
                    case 2 -> {
                        int numOfArticles = getIntFromUser("number of articles: ");
                        lect1 = new Phd(HeadOfCommissionName, lectId, degreeName, salary, degree, numOfArticles);
                        System.out.println("Phd added successfully");
                        nameOk = true;
                    }
                    case 3 -> {
                        int numOfArticles = getIntFromUser("number of articles: ");
                        String certification = getStringFromUser("enter certification: ");
                        lect1 = new Professor(HeadOfCommissionName, lectId, degreeName, salary, degree, numOfArticles, certification);
                        System.out.println("Professor added successfully");
                        nameOk = true;
                    }
                }
                theCollege.add(lect1);
                Commission commission = new Commission(commissionName ,lect1);
                theCollege.addCommission(commission);
                System.out.println("commission added successfully");
                nameOk = true;
            } catch (AlreadyExistException | NotQualifiedException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("General Error");
            }
        }
    }

    private static void addMemberToCommission(College theCollege) {
        String commissionName, lectName;
            try {
                commissionName = getStringFromUser("Commission name");
                lectName = getStringFromUser("name");
                theCollege.IsNameExist(lectName);
                theCollege.addMemberToCommissionTeam(commissionName, lectName);
                System.out.println("Lecturer added successfully");
            } catch (CommissionNotExistException | LecturerNotExistException |
                     LectruerAlreadyHeadOfCommissionException |IlegalMemberAddedException e) {
                System.out.println(e.getMessage());
            } catch (CollegeException e) {
                System.out.println("General error ");
            }

    }

    private static void updateHeadOfCommission(College theCollege) {
        String commissionName, lectName;
        try {
            lectName = getStringFromUser("lecturer name");
            commissionName = getStringFromUser("Commission name ");
            theCollege.isPossibleToChangeHeadOfCommission(commissionName, lectName);
            System.out.println("Head of commission changed");
        } catch (CommissionNotExistException | LecturerNotExistException | LectruerAlreadyHeadOfCommissionException |
                 NotQualifiedException e) {
            System.out.println(e.getMessage());
        } catch (CollegeException e) {
            System.out.println("General error");
        }
    }

    private static void removeMemberFromCommission(College theCollege) {
        String commissionName, lectName;
        try {
            lectName = getStringFromUser("lecturer name");
            commissionName = getStringFromUser("Commission name ");
            theCollege.removeMemberFromCommission(commissionName, lectName);
            System.out.println("Member removed successfully");
        } catch (CommissionNotExistException | LecturerNotExistException e) {
            System.out.println(e.getMessage());
        } catch (CollegeException e) {
            System.out.println("General Error");
        }
    }

    private static void addDepartment(College theCollege) {
        String departmentName;
        int numOfStudents;
            try {
                departmentName = getStringFromUser("department name");
                numOfStudents = getIntFromUser("num of students in the department");
                Department department = new Department(departmentName, numOfStudents);
                theCollege.addDepartment(department);
                System.out.println("Department added successfully");
            } catch (CollegeException e) {
                System.out.println(e.getMessage());
            }


    }

    private static void ShowLecturersAvgSalary(College theCollege) {
        try {
            System.out.println("The average salary in the college is " + theCollege.getLecturersAvgSalary());
        } catch (NoLecturersInCollegeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showDepartmentLecturersAvgSalary(College c1) {
        try {
            String deptName;
            float avg;
            deptName = getStringFromUser("Department name ");
            avg = c1.getDepartmentLecturersAvgSalary(deptName);
            System.out.println("The avg salary is "+avg);
        } catch (CollegeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void ShowAllLecturersDetails(College theCollege) {
        System.out.println(theCollege.getAllLecturersDetails());
    }

    private static void ShowCommissionsDetails(College c1) {
        System.out.println(c1.toString());
    }

    private static void addLecturerToDepartment(College c1) {
        String deptName, lectName;
            try {
                lectName = getStringFromUser("Lecturer name ");
                deptName = getStringFromUser("Department name ");
                c1.addMemberToDepartment(deptName, lectName);
                System.out.println("Lecturer added to department successfully! ");
            } catch (CollegeException e) {
                System.out.println(e.getMessage());
            }
    }

    private static int getIntFromUser(String type) {
        System.out.println("Enter " + type + " :");
        return Integer.parseInt(s.nextLine());
    }

    private static String getStringFromUser(String type) {
        System.out.println("Enter " + type + " :");
        return s.nextLine();
    }

    private static Lecturer createLectruer(College theCollege) {
        Lecturer lect1=null;
        String lectId, lectName, degreeName;
        boolean nameOk = false;
        Lecturer.eDegree degree = null;
        int salary;
        while (!nameOk) {
            try {
                lectName = getStringFromUser("name");
                theCollege.isNameNotExist(lectName);
                degreeName = getStringFromUser("degree name");
                do {
                    salary = getIntFromUser("Salary");
                    if (salary < 0) {
                        System.out.println("Invalid salary try again!");
                    }
                } while (salary < 0);
                do {
                    lectId = getStringFromUser("Id");
                    if (lectId.length() != 9) {
                        System.out.println("Invalid id try again!");
                    }
                } while (lectId.length() != 9);
                boolean degreeOk=false;
                while(!degreeOk) {
                    try {
                        System.out.println("lecturer degree: Bachelor/Master/Phd/Professor");
                        degree = Lecturer.eDegree.valueOf(s.nextLine());
                        degreeOk = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("not one of the options");
                    }
                }
                switch (degree.ordinal()) {
                    case 0, 1 -> {
                        lect1 = new Lecturer(lectName, lectId, degreeName, salary, degree);
                        System.out.println("lecturer added successfully");
                        nameOk = true;
                    }
                    case 2 -> {
                        int numOfArticles = getIntFromUser("number of articles ");
                        lect1 = new Phd(lectName, lectId, degreeName, salary, degree, numOfArticles);
                        System.out.println("Phd added successfully");
                        nameOk = true;
                    }
                    case 3 -> {
                        int numOfArticles = getIntFromUser("number of articles ");
                        String certification = getStringFromUser(" certification ");
                        lect1 = new Professor(lectName, lectId, degreeName, salary, degree, numOfArticles, certification);
                        System.out.println("Professor added successfully");
                        nameOk = true;
                    }
                }

            } catch (AlreadyExistException e) {
                System.out.println(e.getMessage());
            }
        }
        return lect1;
    }

    private static void CompareBetweenLecturers(College c1) {
     String lectName1,lectName2;
     int res;
        try {
            lectName1=getStringFromUser("First lecturer name(must be Phd/Professor) ");
            c1.IsNameExist(lectName1);
            lectName2=getStringFromUser("Second lecturer name(must be Phd/Professor) ");
            c1.IsNameExist(lectName2);
            res=c1.compare(lectName1,lectName2);
            if(res>0){
                System.out.println(lectName1+" have more articles then: "+lectName2);
            }
            else if(res<0){
                System.out.println(lectName2+" have more articles then: "+lectName1);
            }
            else {
                System.out.println("they have the same amount of articles");
            }
        } catch (CollegeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void CompareBetweenCommissions(College c1)   {
        try {
            String comm1 = getStringFromUser("Type the name of first Commission");
            String comm2 = getStringFromUser("Type the name of second Commission");
            Commission firstCommission=c1.getCommissionByName(comm1);
            Commission secondCommission=c1.getCommissionByName(comm2);
            int option=getIntFromUser("Choose by which option:\npress 1: by number of members\npress 2: by numbers of articles");
            if (option == 1) {
                int res = c1.comparebymembers(firstCommission, secondCommission);
                if (res > 0) {
                    System.out.println(comm1+" have more members");
                } else if (res < 0) {
                    System.out.println(comm2+ " have more members");
                } else {
                    System.out.println("they have the same amount of members! ");
                }

            } else if (option == 2) {
                int res=c1.comparebyarticles(firstCommission,secondCommission);
                if (res > 0) {
                    System.out.println(comm1+" have more articles");
                } else if (res < 0) {
                    System.out.println(comm2+ " have more articles");
                } else {
                    System.out.println("they have the same amount of articles! ");
                }

            } else {
                System.out.println("Invalid option: please choose 1 or 2.");
            }
        }
        catch(CollegeException e){
            System.out.println(e.getMessage());
        }
           
        }

    private static void DuplicateCommission(College c1) {
        String commName;
        commName=getStringFromUser("commission name");
        try {
           Commission commission=c1.getCommissionByName(commName);
            c1.dupComm(commission);
            System.out.println("Commission duplicated successfully");
        } catch (CollegeException e) {
            System.out.println(e.getMessage());
        } catch (CloneNotSupportedException e) {
            System.out.println("error");
        }
    }
}
