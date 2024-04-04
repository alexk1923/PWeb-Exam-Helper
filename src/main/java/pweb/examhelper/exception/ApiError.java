package pweb.examhelper.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class ApiError {
    private String errorMessage;
    private HttpStatus code;
    private Date timestamp;

    public ApiError(String errorMessage, HttpStatus code, Date timestamp) {
        this.errorMessage = (errorMessage != null) ? errorMessage : "No message available";
        this.code = code;
        this.timestamp = timestamp;
    }
}
