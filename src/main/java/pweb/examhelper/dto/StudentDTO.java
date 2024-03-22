package pweb.examhelper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;


}
