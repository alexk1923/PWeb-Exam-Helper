package pweb.examhelper.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionGetDTO {
    private Long subjectId;
    private Long quizId;
}
