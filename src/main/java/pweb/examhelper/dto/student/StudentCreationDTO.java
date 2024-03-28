package pweb.examhelper.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class StudentCreationDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
