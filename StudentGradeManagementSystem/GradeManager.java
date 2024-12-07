//Manages the list of students and courses. Handles add, delete, and search operations. This file handles system logic 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradeManager { // List of all students and courses
    private List<Student> students;
    private List<Course> courses;

    public GradeManager() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // Add new students to our system
    public boolean addStudent(String name, String id) {
        if (name == null || name.trim().isEmpty() || id == null || id.trim().isEmpty()) {
            return false;
        }
        if (getStudentById(id) != null) {
            return false; // for duplicate ID
        }
        students.add(new Student(name.trim(), id.trim()));
        return true;
    }

    public boolean updateStudent(String id, String newName) {
        Student s = getStudentById(id);
        if (s == null) return false;
        s.setName(newName);
        return true;
    }

    public boolean deleteStudent(String id) {
        Student s = getStudentById(id);
        if (s == null) return false;
        students.remove(s);
        return true;
    }

    public Student getStudentById(String id) {
        for (Student s : students) {
            if (s.getStudentId().equals(id)) return s;
        }
        return null;
    }

    // This is for course management methods
    public boolean addCourse(String name, String code, String instructor) {
        if (name == null || name.trim().isEmpty() 
            || code == null || code.trim().isEmpty() 
            || instructor == null || instructor.trim().isEmpty()) {
            return false;
        }
        if (getCourseByCode(code) != null) {
            return false; 
        }
        courses.add(new Course(name.trim(), code.trim(), instructor.trim()));
        return true;
    }

    public boolean updateCourse(String code, String newName, String newInstructor) {
        Course c = getCourseByCode(code);
        if (c == null) return false;
        if (newName != null && !newName.trim().isEmpty()) c.setCourseName(newName);
        if (newInstructor != null && !newInstructor.trim().isEmpty()) c.setInstructor(newInstructor);
        return true;
    }

    public boolean deleteCourse(String code) {
        Course c = getCourseByCode(code);
        if (c == null) return false;
        courses.remove(c);
        // Remove this course 
        for (Student s : students) {
            s.removeCourse(code);
        }
        return true;
    }

    public Course getCourseByCode(String code) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(code)) return c;
        }
        return null;
    }

    //Assign Grades
    public boolean assignGradeToStudent(String studentId, String courseCode, int grade) {
        if (grade < 0 || grade > 100) return false;
        Student s = getStudentById(studentId);
        if (s == null) return false;
        Course c = getCourseByCode(courseCode);
        if (c == null) return false;
        if (!s.isEnrolledInCourse(courseCode)) {
            s.enrollInCourse(courseCode);
        }
        s.assignGrade(courseCode, grade);
        return true;
    }

    //Enrollment 
    public boolean enrollStudentInCourse(String studentId, String courseCode) {
        Student s = getStudentById(studentId);
        Course c = getCourseByCode(courseCode);
        if (s == null || c == null) return false;
        s.enrollInCourse(courseCode);
        return true;
    }

    //Searching
    public List<Student> searchStudentsByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name.trim())) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Course> searchCoursesByName(String courseName) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.getCourseName().equalsIgnoreCase(courseName.trim())) {
                result.add(c);
            }
        }
        return result;
    }

    //Reporting
    public void printStudentPerformance(Student s) {
        double avg = s.getAverageGrade();
        String category = getPerformanceCategory(avg);
        System.out.println("Name: " + s.getName());
        System.out.println("ID: " + s.getStudentId());
        System.out.print("Courses and Grades: ");
        if (s.getCourseGrades().isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            System.out.println();
            for (Map.Entry<String, Integer> entry : s.getCourseGrades().entrySet()) {
                String cc = entry.getKey();
                int g = entry.getValue();
                String gradeStr = (g < 0) ? "No grade assigned" : String.valueOf(g);
                Course c = getCourseByCode(cc);
                String cName = (c != null) ? c.getCourseName() : cc;
                System.out.println("  " + cName + " (" + cc + "): " + gradeStr);
            }
        }
        System.out.println("Average Grade: " + (Double.isNaN(avg) ? "N/A" : String.format("%.2f", avg)));
        System.out.println("Performance: " + category);
        System.out.println("---------------------------------");
    }

    public void printClassReport() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }

        double totalSum = 0;
        int totalCount = 0;
        int globalMin = Integer.MAX_VALUE;
        int globalMax = -1;

        for (Student s : students) {
            for (int g : s.getCourseGrades().values()) {
                if (g >= 0 && g <= 100) {
                    totalSum += g;
                    totalCount++;
                    if (g < globalMin) globalMin = g;
                    if (g > globalMax) globalMax = g;
                }
            }
        }

        double classAverage = (totalCount == 0) ? 0.0 : totalSum / totalCount;

        System.out.println("Overall Class Performance:");
        System.out.println("Class Average: " + String.format("%.2f", classAverage));
        if (globalMin == Integer.MAX_VALUE) globalMin = -1;
        System.out.println("Highest Grade: " + (globalMax == -1 ? "N/A" : globalMax));
        System.out.println("Lowest Grade: " + (globalMin == -1 ? "N/A" : globalMin));

        System.out.println("Individual Student Performance:");
        for (Student s : students) {
            double avg = s.getAverageGrade();
            System.out.println("---------------------------------");
            System.out.println("Name: " + s.getName());
            System.out.println("ID: " + s.getStudentId());
            System.out.println("Average Grade: " + (Double.isNaN(avg) ? "N/A" : String.format("%.2f", avg)));
            System.out.println("Performance: " + getPerformanceCategory(avg));
        }
        System.out.println("---------------------------------");
    }

    private String getPerformanceCategory(double avg) {
        if (avg >= 90) return "Excellent";
        else if (avg >= 70) return "Good";
        else return "Needs Improvement";
    }

    public void printStudentsWithDetails(List<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("No students found with that name.");
            return;
        }
        for (Student s : studentList) {
            printStudentPerformance(s);
        }
    }

    public void printCourseEnrollment(List<Course> courseList) {
        if (courseList.isEmpty()) {
            System.out.println("No courses found with that name.");
            return;
        }
        for (Course c : courseList) {
            System.out.println("Course: " + c.getCourseName() + " (" + c.getCourseCode() + ")");
            System.out.println("Instructor: " + c.getInstructor()); // We can show instructor name when we get course
            List<Student> enrolledStudents = getStudentsInCourse(c.getCourseCode());
            if (enrolledStudents.isEmpty()) {
                System.out.println("No students enrolled in this course.");
            } else {
                for (Student s : enrolledStudents) {
                    Integer grade = s.getCourseGrades().get(c.getCourseCode());
                    String gradeStr = (grade == null || grade < 0) ? "No grade assigned" : grade.toString();
                    System.out.println("  Student: " + s.getName() + " (ID: " + s.getStudentId() + ") - Grade: " + gradeStr);
                }
            }
            System.out.println("---------------------------------");
        }
    }

    private List<Student> getStudentsInCourse(String courseCode) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.isEnrolledInCourse(courseCode)) {
                result.add(s);
            }
        }
        return result;
    }
}