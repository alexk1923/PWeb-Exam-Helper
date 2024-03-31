package pweb.examhelper.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pweb.examhelper.dto.question.QuestionDTO;
import pweb.examhelper.entity.Group;
import pweb.examhelper.entity.GroupStudent;
import pweb.examhelper.entity.QuizQuestion;

import java.util.List;

@AllArgsConstructor
@Getter
public class QuizDTO {
    private Long id;
    private String title;
    private Long subjectId;
    private List<QuestionDTO> questions;
}
