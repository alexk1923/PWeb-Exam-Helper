package pweb.examhelper.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AnswerDTOCreation {
    private String text;
    private Boolean isCorrect;
}
