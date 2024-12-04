import java.util.Scanner;

public class Main {
    private static GradeManager gradeManager = new GradeManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to the Student Grade Management System!");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Assign Grades");
            System.out.println("4. Search Records");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    assignGrades();
                    break;
                case 4:
                    searchRecords();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting the system. Goodbye!");
    }

    private static void manageStudents() {
        // Placeholder for managing student functionality
        System.out.println("Managing Students...");
    }

    private static void manageCourses() {
        // Placeholder for managing course functionality
        System.out.println("Managing Courses...");
    }

    private static void assignGrades() {
        // Placeholder for assigning grades functionality
        System.out.println("Assigning Grades...");
    }

    private static void searchRecords() {
        // Placeholder for searching records functionality
        System.out.println("Searching Records...");
    }
}
