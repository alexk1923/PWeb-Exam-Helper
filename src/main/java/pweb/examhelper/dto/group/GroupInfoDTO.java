package pweb.examhelper.dto.group;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupInfoDTO {
    @NonNull
    private Long id;
    @NonNull
    private String name;
}
