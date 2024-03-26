package pweb.examhelper.dto;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    private List<GroupStudentDTO> groups;



}
