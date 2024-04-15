package pweb.examhelper.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pweb.examhelper.logger.LoggingController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> handleInvalidRole(RuntimeException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST, new Date());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> internalServerError(RuntimeException exception) {
        String message = (exception.getMessage() != null) ? exception.getMessage() : "No message available";
        ApiError apiError = new ApiError(message, HttpStatus.INTERNAL_SERVER_ERROR, new Date());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(value = DatabaseUpdateException.class)
    public ResponseEntity<ApiError> databaseUpdateError(RuntimeException exception) {
        String message = (exception.getMessage() != null) ? exception.getMessage() : "No message available";
        ApiError apiError = new ApiError(message, HttpStatus.INTERNAL_SERVER_ERROR, new Date());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity<ApiError> handleTokenExpired(RuntimeException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new Date());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseEntity<ApiError> authException(RuntimeException exception) {
        LoggingController.getLogger().info("Sunt aici bai baiet");
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.UNAUTHORIZED, new Date());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

}
