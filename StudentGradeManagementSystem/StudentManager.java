import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    // Add new students to our system
    public boolean addStudent(String name, String id) {
        if (name == null || name.trim().isEmpty() || id == null || id.trim().isEmpty()) {
            return false;
        }
        if (getStudentById(id) != null) {
            return false; // for duplicate ID
        }

        // create student
        Student newStudent = new Student(name.trim(), id.trim());
        students.add(newStudent);

        return true;
    }

    // Update student by id
    public boolean updateStudent(String id, String newName) {
        Student s = getStudentById(id);

        // if student does not exist
        if (s == null) {
            return false;
        }

        // update student information
        s.setName(newName);

        return true;
    }

    // Delete student by id
    public boolean deleteStudent(String id) {
        Student s = getStudentById(id);

        // if student does not exist
        if (s == null) {
            return false;
        }

        students.remove(s);
        return true;
    }

    // gets student by student id
    public Student getStudentById(String id) {
        for (Student s : students) {
            if (s.getStudentId().equals(id))
                return s;
        }
        return null;
    }

    public void removeCourse(String code) {
        for (Student s : students) {
            s.removeCourse(code);
        }
    }
}