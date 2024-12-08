// I am showing an a course with a course code, name, and instructor.
 // We also tracks student grades for students enrolled in the course.
 
 public class Course {
    private String courseName;
    private String courseCode;
    private String instructor;

    public Course(String courseName, String courseCode, String instructor) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructor = instructor;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setCourseName(String name) {
        if (name == null || name.trim().isEmpty()) return; 
        this.courseName = name.trim();
    }

    public void setInstructor(String instructor) {
        if (instructor == null || instructor.trim().isEmpty()) return;
        this.instructor = instructor.trim();
    }
}
