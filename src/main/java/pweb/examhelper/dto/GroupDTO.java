package pweb.examhelper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class GroupDTO {
    private Long id;
    @NonNull
    private String name;
    private Set<GroupStudentDTO> groupStudents;
    private Integer numberOfStudents;
}
