package pweb.examhelper.dto.group;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pweb.examhelper.enums.Role;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GroupStudentAddDTO {
    @NotNull
    private Long id;
    @NotNull
    private String role;
}
