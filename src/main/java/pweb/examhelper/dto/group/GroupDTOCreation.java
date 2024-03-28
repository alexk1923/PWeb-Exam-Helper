package pweb.examhelper.dto.group;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class GroupDTOCreation {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Long defaultAdminId;

}
