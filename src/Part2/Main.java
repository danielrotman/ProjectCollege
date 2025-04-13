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
            "Press 10 to show the infos of all commission's "
    };
    public static Scanner s;
    public static void main(String[]args){
        int userOption;
        s=new Scanner(System.in);
        String theCollege;
        final int EXIT=0;
        System.out.println("Enter college name: ");
        theCollege=s.nextLine();
        College c1=new College(theCollege);
        do{
            userOption= Showmenu();
            switch (userOption){
                case 0->System.out.println("Done... Bye");
                case 1->addLecturer(c1);
                case 2->option2();
                case 3-> option3();
                case 4-> option4();
                case 5-> option5();
                case 6-> option6();
                case 7-> option7();
                case 8-> option8();
                case 9-> option9();
                case 10-> option10();
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
        return s.nextInt();
    }

    private static void option10() {
    }

    private static void option9() {
    }

    private static void option8() {
    }

    private static void option7() {
    }

    private static void option6() {
    }

    private static void option5() {
    }

    private static void option4() {
    }

    private static void option3() {
    }

    private static void option2() {
    }

    private static void addLecturer(College theCollege) {
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
        System.out.println("Enter lecturer id");
        lectId=s.nextLine();
        Lecturer lect1=new Lecturer(lectName,lectId,degreeName,salary,degree);
        if(theCollege.addLecturer(lect1)){
            System.out.println("added successfully");
        }
        else{
            System.out.println("failed adding");
        }
    }

}
