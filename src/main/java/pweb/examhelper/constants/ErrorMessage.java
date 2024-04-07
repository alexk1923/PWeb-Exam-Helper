package pweb.examhelper.constants;

public final class ErrorMessage {
    private ErrorMessage() {
        // restrict instantiation
    }

    public static final String USERNAME_CONFLICT = "Username already used";
    public static final String EMAIL_CONFLICT = "Email already used";
    public static final String RESOURCE_NOT_FOUND = "The requested resource could not be found.";
    public static final String UPDATE_NOT_FOUND = "Update error. The resource could not be found";
    public static final String DELETE_NOT_FOUND = "Deletion error. The resource could not be found";
    public static final String STUDENT_ADD_NOT_FOUND = "The student id is invalid and could not be added to the group.";
    public static final String STUDENT_DELETE_NOT_FOUND = "The student id is invalid and could not be deleted from the group";
    public static final String INVALID_ROLE = "Invalid role provided. Choose from: ADMIN, PARTICIPANT, READER";
    public static final String INVALID_DEFAULT_ADMIN = "Invalid default admin id provided. The user could not be found";
    public static final String NO_ADMIN_PROVIDED = "No admin has been provided for the group";
    public static final String RESOURCE_CONFLICT = "A resource with the same identifier already exists.";

}
