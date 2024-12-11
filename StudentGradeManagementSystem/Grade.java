
public class Grade {
    private String code;
    private String studentId;
    private float grade;

    public Grade(String code, String studentId, float grade) {
        this.code = code;
        this.studentId = studentId;
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return code;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
