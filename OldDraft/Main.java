// This is where user will start the program. We are doing a CLI for managing students, courses, and grades 

import java.util.List;
import java.util.Scanner;

public class Main {
    private static GradeManager manager = new GradeManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        startMenu(); // starts the main menu for user to interact
    }

    private static void startMenu() {
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
                    manageStudentsMenu();
                    break;
                case "2":
                    manageCoursesMenu();
                    break;
                case "3":
                    assignGradesMenu();
                    break;
                case "4":
                    searchRecordsMenu();
                    break;
                case "5":
                    generateReportsMenu();
                    break;
                case "6":
                    System.out.println(
                            "\nThank you for using the Student Grade Management System! This is a reminder that all data will be lost.");
                    return;
                default:
                    System.out.println("Invalid input. Please select a valid option (1-6).");
            }
        }
    }

    private static void manageStudentsMenu() {
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

    private static void manageCoursesMenu() {
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

    private static void assignGradesMenu() {
        System.out.println("\nAssign Grades:");
        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine().trim();
        if (manager.getStudentById(studentId) == null) {
            System.out.println("No student found with that ID.");
            return;
        }

        System.out.print("\nEnter Course Code: ");
        String courseCode = sc.nextLine().trim();
        if (manager.getCourseByCode(courseCode) == null) {
            System.out.println("No course found with that code.");
            return;
        }

        System.out.print("Enter Grade (0-100): ");
        String gradeStr = sc.nextLine().trim();
        int grade;
        try {
            grade = Integer.parseInt(gradeStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Grade must be a number between 0 and 100.");
            return;
        }

        boolean success = manager.assignGradeToStudent(studentId, courseCode, grade);
        if (success) {
            System.out.println("Grade assigned successfully!");
        } else {
            System.out.println("Failed to assign grade. Check if inputs are valid.");
        }
    }

    private static void searchRecordsMenu() { // To display the search menu and handles user choices
        while (true) {
            System.out.println("\nSearch Records:");
            System.out.println("1. Search by Student Name");
            System.out.println("2. Search by Course Name");
            System.out.println("3. Back to Main Menu");

            System.out.print("Please select an option: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    searchByStudentName();
                    break;
                case "2":
                    searchByCourseName();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid input. Please choose a valid option (1-3).");
            }
        }
    }

    private static void generateReportsMenu() { // It will show a performance report for all students added to the
                                                // system
        System.out.println("\nGenerate Reports:");
        manager.printClassReport();
    }

    // These are the student operations
    private static void addStudent() {
        System.out.print("\nEnter Student Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine().trim();

        boolean success = manager.addStudent(name, id);
        if (success) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student. Check if the ID is unique and inputs are valid.");
        }
    }

    private static void updateStudent() {
        System.out.print("\nEnter Student ID to update: ");
        String id = sc.nextLine().trim();
        if (manager.getStudentById(id) == null) {
            System.out.println("No student found with that ID.");
            return;
        }

        System.out.print("Enter new name (leave blank to skip): ");
        String newName = sc.nextLine().trim();
        boolean success = manager.updateStudent(id, newName);
        if (success) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Failed to update student.");
        }
    }

    private static void deleteStudent() {
        System.out.print("\nEnter Student ID to delete: ");
        String id = sc.nextLine().trim();
        boolean success = manager.deleteStudent(id);
        if (success) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("No student found with that ID.");
        }
    }

    private static void enrollStudentInCourse() {
        System.out.print("\nEnter Student ID: ");
        String studentId = sc.nextLine().trim();
        if (manager.getStudentById(studentId) == null) {
            System.out.println("No student found with that ID.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine().trim();
        if (manager.getCourseByCode(courseCode) == null) {
            System.out.println("No course found with that code.");
            return;
        }

        boolean success = manager.enrollStudentInCourse(studentId, courseCode);
        if (success) {
            System.out.println("Student enrolled in course successfully!");
        } else {
            System.out.println("Failed to enroll student in course.");
        }
    }

    // These are for the course operations
    private static void addCourse() {
        System.out.print("\nEnter Course Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine().trim();
        System.out.print("Enter Instructor Name: ");
        String instructor = sc.nextLine().trim();

        boolean success = manager.addCourse(name, code, instructor);
        if (success) {
            System.out.println("Course added successfully!");
        } else {
            System.out.println("Failed to add course. Check if the code is unique and inputs are valid.");
        }
    }

    private static void updateCourse() {
        System.out.print("\nEnter Course Code to update: ");
        String code = sc.nextLine().trim();
        if (manager.getCourseByCode(code) == null) {
            System.out.println("No course found with that code.");
            return;
        }

        System.out.print("Enter new course name (leave blank to skip): ");
        String newName = sc.nextLine().trim();
        System.out.print("Enter new instructor name (leave blank to skip): ");
        String newInstructor = sc.nextLine().trim();

        boolean success = manager.updateCourse(code, newName, newInstructor);
        if (success) {
            System.out.println("Course updated successfully!");
        } else {
            System.out.println("Failed to update course.");
        }
    }

    private static void deleteCourse() {
        System.out.print("\nEnter Course Code to delete: ");
        String code = sc.nextLine().trim();
        boolean success = manager.deleteCourse(code);
        if (success) {
            System.out.println("Course deleted successfully!");
        } else {
            System.out.println("No course found with that code.");
        }
    }

    // When we need to search
    private static void searchByStudentName() {
        System.out.print("\nEnter Student Name: ");
        String name = sc.nextLine().trim();
        List<Student> found = manager.searchStudentsByName(name);
        manager.printStudentsWithDetails(found);
    }

    private static void searchByCourseName() {
        System.out.print("\nEnter Course Name: ");
        String courseName = sc.nextLine().trim();
        List<Course> found = manager.searchCoursesByName(courseName);
        manager.printCourseEnrollment(found);
    }
}
