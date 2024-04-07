package pweb.examhelper.dto.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AnswerDTOCreation {
    @NotEmpty
    @NotNull
    private String text;

    @NotNull
    private Boolean isCorrect;
}
