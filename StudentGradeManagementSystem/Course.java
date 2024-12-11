import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private String code;
    private String instructor;
    private List<Student> enrolledStudents;

    public Course(String courseName, String code, String instructor) {
        this.courseName = courseName;
        this.code = code;
        this.instructor = instructor;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return code;
    }

    public String getInstructor() {
        return instructor;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public void setCourseName(String name) {
        if (name == null || name.trim().isEmpty())
            return;
        this.courseName = name.trim();
    }

    public void setInstructor(String instructor) {
        if (instructor == null || instructor.trim().isEmpty())
            return;
        this.instructor = instructor.trim();
    }
}
