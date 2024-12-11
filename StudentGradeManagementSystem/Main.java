// This is where user will start the program. We are doing a CLI for managing students, courses, and grades 

import java.util.List;
import java.util.Scanner;

public class Main {
    private static GradeManager gradeManager = new GradeManager();
    private static StudentManager studentManager = new StudentManager();
    private static CourseManager courseManager = new CourseManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("Welcome to the Student Grade Management System!");

        while (true) { 
            System.out.print("Enter (s) if you are a student, (f) if you are faculty, or (q) to quit: "); // CHANGED
            String userType = sc.nextLine().trim().toLowerCase();
            if (userType.equals("s") || userType.equals("f")) {
                runMainMenu(userType);
            } else if (userType.equals("q")) { 
                System.out.println("Exiting the program. Reminder that all data will be lost!");
                break; 
            } else {
                System.out.println("Invalid input, try again with 's', 'f', or 'q' to quit.");
            }
        }
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
                    if (userType.equals("f")) {
                        manageStudents();
                    } else {
                        System.out.println("Invalid input. Please select a valid option.");
                    }
                    break;
                case "2":
                    if (userType.equals("f")) {
                        manageCourses();
                    } else {
                        System.out.println("Invalid input. Please select a valid option.");
                    }
                    break;
                case "3":
                    if (userType.equals("f")) {
                        assignGrades();
                    } else {
                        System.out.println("Invalid input. Please select a valid option.");
                    }
                    break;
                case "4":
                    searchRecords();
                    break;
                case "5":
                    if (userType.equals("f")) {
                        generateReport();
                    } else {
                        System.out.println("Invalid input. Please select a valid option.");
                    }
                    break;
                case "6":
                    
                    return; 
                default:
                    System.out.println("Invalid input. Please select a valid option.");
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

    private static void enrollStudentInCourse() {
        System.out.print("\nEnter Student ID: ");
        String studentId = sc.nextLine().trim();

        if (studentManager.getStudentById(studentId) == null) {
            System.out.println("No student found with that ID.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine().trim();

        if (courseManager.getCourseByCode(courseCode) == null) {
            System.out.println("No course found with that code.");
            return;
        }

        Course newCourse = courseManager.getCourseByCode(courseCode);
        boolean success = studentManager.enrollStudentInCourse(studentId, newCourse);

        if (success) {
            System.out.println("Student enrolled in course successfully!");
        } else {
            System.out.println("Failed to enroll student in course.");
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

        if (success) {
            studentManager.unenrollStudentFromCourse(code);
            System.out.println("Course deleted successfully!");
        } else {
            System.out.println("No course found with that code.");
        }
    }

    private static void assignGrades() {
        System.out.println("\nAssign Grades:");
        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine().trim();
        if (studentManager.getStudentById(studentId) == null) {
            System.out.println("Student doesn't exist.");
            return;
        }
    
        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine().trim();
        if (courseManager.getCourseByCode(courseCode) == null) {
            System.out.println("Course doesn't exist.");
            return;
        }
    
        System.out.print("Enter Grade (0-100): ");
        float grade;
        try {
            grade = Float.parseFloat(sc.nextLine().trim());
            if (grade < 0 || grade > 100) {
                System.out.println("Invalid input. Grade must be between 0 and 100.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }
        gradeManager.setGrades(courseCode, studentId, grade, courseManager, studentManager);
    }

    private static void searchRecords() {
        while (true) {
            System.out.println("\nSearch Records:");
            System.out.println("1. Search by Student Name");
            System.out.println("2. Search by Course Name");
            System.out.println("3. Back to Main Menu");

            System.out.print("Please select an option: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    searchStudents();
                    break;
                case "2":
                    searchCourses();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid input. Please choose a valid option (1-3).");
            }
        }
    }

    private static void searchStudents() {
        System.out.print("\nEnter Student Name: ");
        String name = sc.nextLine().trim();
        if (studentManager.getStudentByName(name) == null) {
            System.out.println("Student doesn't exist.");
        } else {
            displayStudentReport(name, gradeManager);
        }
    }

    public static void displayStudentReport(String studentName, GradeManager gradeManager) {
        Student s = studentManager.getStudentByName(studentName);
        System.out.println("Name: " + s.getName());
        System.out.println("ID: " + s.getStudentId());
        List<Course> enrolledCourses = s.getEnrolledCourses();
        System.out.println("Enrolled Courses:");
        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            for (Course course : enrolledCourses) {
                Float grade = gradeManager.getGrades(s.getStudentId(), course.getCourseCode());
                String gradeStr = (grade == null) ? "No grade assigned" : String.format("%.2f", grade);
                System.out.println("  - " + course.getCourseName() + " (" + course.getCourseCode() + "): " + gradeStr);
            }
        }
        double avg = gradeManager.getAverageGrade(s.getStudentId());
        float max = gradeManager.getMaxGrade(s.getStudentId());
        float min = gradeManager.getMinGrade(s.getStudentId());

        System.out.println("\nGrades:");
        System.out.println("Average Grade: " + (Double.isNaN(avg) ? "N/A" : String.format("%.2f", avg)));
        System.out.println("Highest Grade: " + (Float.isNaN(max) ? "N/A" : String.format("%.2f", max)));
        System.out.println("Lowest Grade: " + (Float.isNaN(min) ? "N/A" : String.format("%.2f", min)));
    
        System.out.println("---------------------------------");
    }

    private static void searchCourses() {
        System.out.print("\nEnter Course Name: ");
        String courseName = sc.nextLine().trim();
        Course course = courseManager.getCourseByName(courseName); 
        if (course == null) {
            System.out.println("Course doesn't exist.");
        } else {
            displayCourseReport(course);
        }
    }

    private static void displayCourseReport(Course course) {
        System.out.println("Course Name: " + course.getCourseName());
        System.out.println("Course Code: " + course.getCourseCode());
        System.out.println("Instructor: " + course.getInstructor());
        System.out.println("Enrolled Students:");
    
        List<Student> enrolledStudents = studentManager.getStudentsInCourse(course.getCourseCode(), courseManager);
        if (enrolledStudents.isEmpty()) {
            System.out.println("No students are currently enrolled in this course.");
        } else {
            for (Student s : enrolledStudents) {
                Float grade = gradeManager.getGrades(s.getStudentId(), course.getCourseCode());
                String gradeStr = (grade == null) ? "No grade assigned" : String.format("%.2f", grade);
                System.out.println("Student: " + s.getName() + " (ID: " + s.getStudentId() + ") - Grade: " + gradeStr);
            }
        }
        System.out.println("---------------------------------");
    }

    private static void generateReport() {
        System.out.print("Enter Course Code to generate report: ");
        String courseCode = sc.nextLine().trim();
        gradeManager.generateCourseReport(courseCode, courseManager, studentManager);
    }
}