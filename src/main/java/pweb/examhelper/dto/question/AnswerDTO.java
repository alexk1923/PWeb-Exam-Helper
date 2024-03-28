package pweb.examhelper.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AnswerDTO {
    private long id;
    private String text;
    private Boolean isCorrect;
}
