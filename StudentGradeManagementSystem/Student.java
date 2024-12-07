//I am representing a student with an ID, name, and a list of enrolled courses.

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private String studentId;
    private Map<String, Integer> courseGrades; // Needed this as it is a map of course codes to grades

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.courseGrades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) return; 
        this.name = name.trim();
    }

    public Map<String, Integer> getCourseGrades() {
        return courseGrades;
    }

    public void assignGrade(String courseCode, int grade) {// assign grade to a student for a specific course
        courseGrades.put(courseCode, grade);
    }

    public boolean isEnrolledInCourse(String courseCode) { // check if student is enrolled
        return courseGrades.containsKey(courseCode);
    }

    public void enrollInCourse(String courseCode) {
        if (!courseGrades.containsKey(courseCode)) {
            // I did -1 to indicate enrolled but no grade is actually assign for now
            courseGrades.put(courseCode, -1);
        }
    }

    public void removeCourse(String courseCode) {
        courseGrades.remove(courseCode);
    }
     // These are to calculate the grade for the student across all courses
    public double getAverageGrade() {
        if (courseGrades.isEmpty()) return 0.0;
        double sum = 0;
        int count = 0;
        for (int g : courseGrades.values()) {
            if (g >= 0 && g <= 100) {
                sum += g;
                count++;
            }
        }
        return count == 0 ? 0.0 : sum / count;
    }

    public int getMinGrade() {
        int min = Integer.MAX_VALUE;
        boolean found = false;
        for (int g : courseGrades.values()) {
            if (g >= 0 && g <= 100) {
                found = true;
                if (g < min) min = g;
            }
        }
        return found ? min : -1;
    }

    public int getMaxGrade() {
        int max = -1;
        for (int g : courseGrades.values()) {
            if (g >= 0 && g <= 100 && g > max) {
                max = g;
            }
        }
        return max;
    }
}

