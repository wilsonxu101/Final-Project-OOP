import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private List<Course> courses;

    public CourseManager() {
        this.courses = new ArrayList<>();
    }

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

        if (c == null)
            return false;

        if (newName != null && !newName.trim().isEmpty())
            c.setCourseName(newName);

        if (newInstructor != null && !newInstructor.trim().isEmpty())
            c.setInstructor(newInstructor);

        return true;
    }

    public boolean deleteCourse(String code) {
        Course c = getCourseByCode(code);
        if (c == null)
            return false;
        courses.remove(c);

        return true;
    }

    public Course getCourseByCode(String code) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(code))
                return c;
        }
        return null;
    }
}