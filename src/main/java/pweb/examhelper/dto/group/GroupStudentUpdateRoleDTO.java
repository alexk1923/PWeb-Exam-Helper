package pweb.examhelper.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GroupStudentUpdateRoleDTO {
    private Long studentId;
    private String newRole;
}
