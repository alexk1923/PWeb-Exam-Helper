package pweb.examhelper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleStudentNotFound(RuntimeException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND, new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(value = ResourceAlreadyExists.class)
    public ResponseEntity<ApiError> handleStudentUsernameConflict(RuntimeException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.CONFLICT, new Date());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> internalServerError(RuntimeException exception) {
        String message = (exception.getMessage() != null) ? exception.getMessage() : "No message available";
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new Date());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

}
