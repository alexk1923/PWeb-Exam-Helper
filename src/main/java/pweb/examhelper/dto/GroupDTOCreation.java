package pweb.examhelper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GroupDTOCreation {
    private GroupDTO groupDTO;
    private Long defaultAdminId;

}
