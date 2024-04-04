package pweb.examhelper.exception;

import javax.management.ConstructorParameters;

public class ResourceAlreadyExists extends RuntimeException{
    public ResourceAlreadyExists(String message) {
        super(message);
    }
}
