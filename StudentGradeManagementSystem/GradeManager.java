import java.util.ArrayList; // This file handles system logic
import java.util.List;
import java.util.Optional;

public class GradeManager {
    private List<Student> students;
    private List<Course> courses;

    public GradeManager() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String studentId) {
        students.removeIf(student -> student.getId().equals(studentId));
    }

    public Optional<Student> findStudentById(String studentId) {
        return students.stream().filter(student -> student.getId().equals(studentId)).findFirst();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(String courseCode) {
        courses.removeIf(course -> course.getCode().equals(courseCode));
    }

    public Optional<Course> findCourseByCode(String courseCode) {
        return courses.stream().filter(course -> course.getCode().equals(courseCode)).findFirst();
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
