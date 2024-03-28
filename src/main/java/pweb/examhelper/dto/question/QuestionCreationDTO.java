package pweb.examhelper.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class QuestionCreationDTO {
    private String text;
    private List<AnswerDTOCreation> answers;
}
