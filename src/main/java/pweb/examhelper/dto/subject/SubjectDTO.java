package pweb.examhelper.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pweb.examhelper.dto.quiz.QuizDTO;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SubjectDTO {
    private Long id;
    private String name;
    private List<QuizDTO> quizList;
}
