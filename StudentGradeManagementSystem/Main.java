// This is where user will start the program. We are doing a CLI for managing students, courses, and grades 

import java.util.List;
import java.util.Scanner;

public class Main {
    private static GradeManager gradeManager = new GradeManager();
    private static StudentManager studentManager = new StudentManager();
    private static CourseManager courseManager = new CourseManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // user specifies if student or faculty
        System.out.println("Welcome to the Student Grade Management System!");

        String userType = "";

        while (true) {
            System.out.print("Enter (s) if you are a student or (f) if you are faculty: ");
            userType = sc.nextLine().trim();
            if (userType.equals("s") || userType.equals("f")) {
                break;
            } else {
                System.out.println("Invalid input, try again with either 's' or 'f'.");
            }
        }

        runMainMenu(userType.toLowerCase()); // starts the main menu for user to interact
    }

    private static void runMainMenu(String userType) {
        while (true) {
            // print menu options
            System.out.println("\nMenu Options: ");
            if (userType.equals("f")) {
                System.out.println("1. Manage Students");
                System.out.println("2. Manage Courses");
                System.out.println("3. Assign Grades");
                System.out.println("4. Search Records");
                System.out.println("5. Generate Reports");
            } else {
                System.out.println("4. Search Records");
            }
            System.out.println("6. Exit");

            System.out.print("Please select an option: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    manageStudents();
                    break;
                case "2":
                    manageCourses();
                    break;
                // case "3":
                // assignGradesMenu();
                // break;
                // case "4":
                // searchRecordsMenu();
                // break;
                // case "5":
                // generateReportsMenu();
                // break;
                case "6":
                    System.out.println(
                            "\nThank you for using the Student Grade Management System! This is a reminder that all data will be lost.");
                    return;
                default:
                    System.out.println("Invalid input. Please select a valid option (1-6).");
            }
        }
    }

    private static void manageStudents() {
        while (true) {
            System.out.println("\nManage Students:");
            System.out.println("1. Add Student Record");
            System.out.println("2. Update Student Record");
            System.out.println("3. Delete Student Record");
            System.out.println("4. Enroll Student in Course");
            System.out.println("5. Back to Main Menu"); // return to main menu

            System.out.print("Please select an option: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    updateStudent();
                    break;
                case "3":
                    deleteStudent();
                    break;
                case "4":
                    enrollStudentInCourse();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid input. Please choose a valid option (1-5).");
            }
        }
    }

    private static void manageCourses() {
        while (true) {
            System.out.println("\nManage Courses:");
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Back to Main Menu");

            System.out.print("Please select an option: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addCourse();
                    break;
                case "2":
                    updateCourse();
                    break;
                case "3":
                    deleteCourse();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("\nInvalid input. Please choose a valid option (1-4)."); // data validation and
                                                                                                // error handling
            }
        }
    }
}