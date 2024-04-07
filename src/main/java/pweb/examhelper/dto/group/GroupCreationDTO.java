package pweb.examhelper.dto.group;

import jakarta.validation.Valid;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupCreationDTO {
    @NonNull @Valid
    private String name;
    @NonNull @Valid
    private Long defaultAdminId;

}
