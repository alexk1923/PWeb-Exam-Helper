package pweb.examhelper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pweb.examhelper.enums.Role;

@Data
@AllArgsConstructor
public class GroupStudentDTO {
    private Long id;
    private Long groupId;
    private Long studentId;
    private Role role;
}
