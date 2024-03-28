package pweb.examhelper.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    private Long id;
    private String text;
    private List<AnswerDTO> answers;
}
