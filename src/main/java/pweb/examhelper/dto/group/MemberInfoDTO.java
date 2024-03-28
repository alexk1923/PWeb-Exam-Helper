package pweb.examhelper.dto.group;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberInfoDTO {
    @NotNull
    private Long id;
    @NonNull
    private String username;
}
