import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String studentId;
    private List<Course> enrolledCourses;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            return;
        this.name = name.trim();
    }

    public boolean addCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            return true;
        }
        return false;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
}
