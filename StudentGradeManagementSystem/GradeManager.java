import java.util.ArrayList;
import java.util.List;

public class GradeManager { // List of all students and courses
    private List<Grade> gradeList;

    public GradeManager() {
        this.gradeList = new ArrayList<>();
    }

    public void addGrade(Grade grade) {
        for (Grade g : gradeList) {
            if (g.getStudentId().equals(grade.getStudentId()) &&
                g.getCourseCode().equals(grade.getCourseCode())) {
                g.setGrade(grade.getGrade());
                return;
            }
        }
        gradeList.add(grade);
    }

    public Float getGrades(String studentId, String courseCode) {
        for (Grade g : gradeList) {
            if (g.getStudentId().equals(studentId) &&
                g.getCourseCode().equals(courseCode)) {
                return g.getGrade();
            }
        }
        return null;
    }

    public List<Grade> getGradesByStudent(String studentId) {
        List<Grade> studentGrades = new ArrayList<>();
        for (Grade g : gradeList) {
            if (g.getStudentId().equals(studentId)) {
                studentGrades.add(g);
            }
        }
        return studentGrades;
    }

    public void setGrades(String courseId, String studentId, float grade, 
    CourseManager courseManager, StudentManager studentManager) {
        if (courseManager.getCourseByCode(courseId) == null) {
            System.out.println("Course doesn't exist.");
            return;
        }

        if (studentManager.getStudentById(studentId) == null) {
            System.out.println("Course doesn't exist.");
            return;
        }

        for (Grade g : gradeList) {
            if (g.getCourseCode().equals(courseId) && g.getStudentId().equals(studentId)) {
                g.setGrade(grade); 
                System.out.println("Grade updated: " + g.getGrade());
                return;
            }
        }
        
        Grade newGrade = new Grade(courseId, studentId, grade);
        save(newGrade);
        System.out.println("Grade assigned.");
    }

    public double getAverageGrade(String studentId) {
        float total = 0;
        int count = 0;
        for (Grade g : gradeList) {
            if (g.getStudentId().equals(studentId)) {
                total += g.getGrade();
                count++;
            }
        }
        return count == 0 ? 0.0 : (double) total / count;
    }

    public Float getMaxGrade(String studentId) {
        float max = Float.NEGATIVE_INFINITY;

        for (Grade g : gradeList) {
            if (g.getStudentId().equals(studentId)) {
                max = Math.max(max, g.getGrade());
            }
        }
        return max;
    }

    public Float getMinGrade(String studentId) {
        float min = Float.POSITIVE_INFINITY;

        for (Grade g : gradeList) {
            if (g.getStudentId().equals(studentId)) {
                min = Math.min(min, g.getGrade());
            }
        }
        return min;
    }

    public void printStudentGrades(String studentId) {
        boolean hasGrades = false;
        System.out.println("Grades for Student: " + studentId);
        for (Grade g : gradeList) {
            if (g.getStudentId().equals(studentId)) {
                hasGrades = true;
            }
        }

        if (!hasGrades) {
            System.out.println("No grades assigned for this student.");
        } else {
            System.out.println("Average Grade: " + getAverageGrade(studentId));
            System.out.println("Highest Grade: " + getMaxGrade(studentId));
            System.out.println("Lowest Grade: " + getMinGrade(studentId));
        }
    }

    public void save(Grade grade) {
        gradeList.add(grade);
        for (Grade g : gradeList) {
            System.out.println("Student ID: " + g.getStudentId() + ", Course Code: " + g.getCourseCode() + ", Grade: " + g.getGrade());
        }
    }
    
    public void generateCourseReport(String courseCode, CourseManager courseManager, StudentManager studentManager) {
        Course course = courseManager.getCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course doesn't exist.");
            return;
        }

        System.out.println("\nCourse Name and Course code: " + course.getCourseName() + " (" + course.getCourseCode() + ")");
        System.out.println("Instructor: " + course.getInstructor());
    
        List<Student> enrolledStudents = studentManager.getStudentsInCourse(courseCode, courseManager);

        if (enrolledStudents.isEmpty()) {
            System.out.println("No students enrolled in the course.");
            return;
        }

        float totalGrades = 0;
        int gradedCount = 0;
        float highestGrade = Float.MIN_VALUE;
        float lowestGrade = Float.MAX_VALUE;

        System.out.println("\nIndividual Student Performance:");
        for (Student s : enrolledStudents) {
            Float grade = getGrades(s.getStudentId(), course.getCourseCode());
            String gradeStr = (grade == null) ? "No grade assigned" : String.format("%.2f", grade);
            if (grade != null && grade >= 0 && grade <= 100) {
                totalGrades += grade;
                gradedCount++;
                highestGrade = Math.max(highestGrade, grade);
                lowestGrade = Math.min(lowestGrade, grade);
            }
            System.out.println("---------------------------------");
            System.out.println("Name: " + s.getName());
            System.out.println("ID: " + s.getStudentId());
            System.out.println("Grade: " + gradeStr);
        }
        System.out.println("---------------------------------");

        float averageGrade = (gradedCount > 0) ? (totalGrades / gradedCount) : 0.0f;

        System.out.println("\nCourse Statistics:");
        System.out.println("Average Grade: " + (gradedCount > 0 ? String.format("%.2f", averageGrade) : "N/A"));
        System.out.println("Highest Grade: " + (gradedCount > 0 ? String.format("%.2f", highestGrade) : "N/A"));
        System.out.println("Lowest Grade: " + (gradedCount > 0 ? String.format("%.2f", lowestGrade) : "N/A"));
    }
}