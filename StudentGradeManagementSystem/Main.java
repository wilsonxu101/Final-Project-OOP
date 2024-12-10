// This is where user will start the program. We are doing a CLI for managing students, courses, and grades 

import java.util.List;
import java.util.Scanner;

public class Main {
    // private static GradeManager gradeManager = new GradeManager();
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
                // case "4":
                // enrollStudentInCourse();
                // break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid input. Please choose a valid option (1-5).");
            }
        }
    }

    // student operations
    private static void addStudent() {
        System.out.print("\nEnter Student Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine().trim();

        boolean success = studentManager.addStudent(name, id);

        if (success) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student. Check if the ID is unique and inputs are valid.");
        }
    }

    private static void updateStudent() {
        System.out.print("\nEnter Student ID to update: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter new name: ");
        String newName = sc.nextLine().trim();

        boolean success = studentManager.updateStudent(id, newName);

        if (success) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Failed to update student.");
        }
    }

    private static void deleteStudent() {
        System.out.print("\nEnter Student ID to delete: ");
        String id = sc.nextLine().trim();

        boolean success = studentManager.deleteStudent(id);

        if (success) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("No student found with that ID.");
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
                    System.out.println("\nInvalid input. Please choose a valid option (1-4).");
            }
        }
    }

    // course operations
    private static void addCourse() {
        System.out.print("\nEnter Course Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine().trim();
        System.out.print("Enter Instructor Name: ");
        String instructor = sc.nextLine().trim();

        boolean success = courseManager.addCourse(name, code, instructor);

        if (success) {
            System.out.println("Course added successfully!");
        } else {
            System.out.println("Failed to add course. Check if the code is unique and inputs are valid.");
        }
    }

    private static void updateCourse() {
        System.out.print("\nEnter Course Code to update: ");
        String code = sc.nextLine().trim();

        if (courseManager.getCourseByCode(code) == null) {
            System.out.println("No course found with that code.");
            return;
        }

        System.out.print("Enter new course name (leave blank to skip): ");
        String newName = sc.nextLine().trim();
        System.out.print("Enter new instructor name (leave blank to skip): ");
        String newInstructor = sc.nextLine().trim();

        boolean success = courseManager.updateCourse(code, newName, newInstructor);
        if (success) {
            System.out.println("Course updated successfully!");
        } else {
            System.out.println("Failed to update course.");
        }
    }

    private static void deleteCourse() {
        System.out.print("\nEnter Course Code to delete: ");
        String code = sc.nextLine().trim();

        boolean success = courseManager.deleteCourse(code);
        studentManager.removeCourse(code);

        if (success) {
            System.out.println("Course deleted successfully!");
        } else {
            System.out.println("No course found with that code.");
        }
    }
}