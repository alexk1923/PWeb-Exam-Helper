package pweb.examhelper.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import pweb.examhelper.dto.quiz.QuizCreationDTO;
import pweb.examhelper.dto.quiz.QuizDTO;
import pweb.examhelper.entity.Quiz;

@AllArgsConstructor
@Getter
public class SubjectAddQuizDTO {
    @NonNull
    private Long subjectId;
    @NonNull
    private String quizName;
}
