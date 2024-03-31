package pweb.examhelper.mapper;

import pweb.examhelper.dto.quiz.QuizCreationDTO;
import pweb.examhelper.dto.quiz.QuizDTO;
import pweb.examhelper.entity.Quiz;
import pweb.examhelper.entity.Subject;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class QuizMapper {

    public static Quiz mapToQuiz(String title, Subject subject) {
        return new Quiz(title, new ArrayList<>(), subject);
    }

    public static QuizDTO mapToQuizDTO(Quiz quiz) {
        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getSubject().getId(),
                quiz.getQuestionList().stream().map(qq -> QuestionMapper.mapToQuestionDTO(qq.getQuestion()))
                        .collect(Collectors.toList()));
    }
}


