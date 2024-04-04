package pweb.examhelper.constants;

public final class ErrorMessage {
    private ErrorMessage() {
        // restrict instantiation
    }

    public static final String USERNAME_CONFLICT = "Username already used";
    public static final String EMAIL_CONFLICT = "Email already used";
    public static final String RESOURCE_NOT_FOUND = "The requested resource could not be found.";
    public static final String RESOURCE_CONFLICT = "A resource with the same identifier already exists.";

}
