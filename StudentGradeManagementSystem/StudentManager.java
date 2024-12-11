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

    // get student by name 
    public Student getStudentByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public List<Student> getStudentsInCourse(String courseCode, CourseManager courseManager) {
        Course course = courseManager.getCourseByCode(courseCode);
        return (course != null) ? course.getEnrolledStudents() : new ArrayList<>();
    }    

    // enroll student to course
    public boolean enrollStudentInCourse(String id, Course course) {
        Student s = getStudentById(id);

        // if student does not exist
        if (s == null) {
            return false;
        }

        boolean courseAdded = s.addCourse(course);

        if (courseAdded) {
            course.addStudent(s);
            return true;
        } else {
            return false;
        }
    }

    // unenroll students from course when it is deleted
    public void unenrollStudentFromCourse(String code) {
        for (Student s : students) {
            List<Course> courses = s.getEnrolledCourses();

            courses.removeIf(course -> course.getCourseCode().equals(code));
        }
    }
}