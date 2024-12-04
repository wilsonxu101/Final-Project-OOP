import java.util.HashMap; // This file represents course data
import java.util.Map;

public class Course {
    private String code;
    private String name;
    private String instructor;
    private Map<String, Integer> studentGrades;

    public Course(String code, String name, String instructor) {
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.studentGrades = new HashMap<>();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void assignGrade(String studentId, int grade) {
        studentGrades.put(studentId, grade);
    }

    public Map<String, Integer> getStudentGrades() {
        return studentGrades;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }
}
