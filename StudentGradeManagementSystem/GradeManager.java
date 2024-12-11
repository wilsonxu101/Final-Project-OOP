import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradeManager { // List of all students and courses
    private List<Grade> gradeList;

    public GradeManager() {
        this.gradeList = new ArrayList<>();
    }

    public Float getGrade(String studentId, String courseCode) {
        for (Grade g : gradeList) {
            if (g.getStudentId().equals(studentId) && g.getCourseCode().equals(courseCode)) {
                return g.getGrade();
            }
        }
        return null;
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
        System.out.println("Grades for Student ID: " + studentId);
        for (Grade g : gradeList) {
            if (g.getStudentId().equals(studentId)) {
                hasGrades = true;
                System.out.println("  Course: " + g.getCourseCode() + " | Grade: " + g.getGrade());
            }
        }

        if (!hasGrades) {
            System.out.println("  No grades available for this student.");
        } else {
            System.out.println("  Average Grade: " + getAverageGrade(studentId));
            System.out.println("  Highest Grade: " + getMaxGrade(studentId()));
            System.out.println("  Lowest Grade: " + getMinGrade(studentId()));
        }
    }

    public void save(Grade grade) {
        gradeList.add(grade);
    }
}

