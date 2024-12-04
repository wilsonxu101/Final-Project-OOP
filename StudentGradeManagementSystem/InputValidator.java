public class InputValidator {
    public static boolean isValidGrade(int grade) {
        return grade >= 0 && grade <= 100;
    }

    public static boolean isValidId(String id) {
        return id != null && !id.trim().isEmpty();
    }
}

// Should contains utility classes that provide helper methods:
//InputValidator ensures user input (e.g., grades, IDs) is valid.
