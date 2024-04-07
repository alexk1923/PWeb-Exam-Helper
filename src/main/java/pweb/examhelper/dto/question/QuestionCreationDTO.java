package pweb.examhelper.dto.question;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class QuestionCreationDTO {
    @NotBlank
    @NotNull
    private String text;
    @NotEmpty
    private List<@Valid AnswerDTOCreation> answers;
}
