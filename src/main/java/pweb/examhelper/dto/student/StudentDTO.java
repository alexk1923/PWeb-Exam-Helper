package pweb.examhelper.dto.student;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pweb.examhelper.dto.group.GroupInfoDTO;
import pweb.examhelper.dto.group.GroupStudentDTO;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;

    private List<GroupInfoDTO> groups;



}
