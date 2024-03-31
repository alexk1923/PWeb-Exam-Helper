package pweb.examhelper.service;

import pweb.examhelper.dto.quiz.QuestionGetDTO;
import pweb.examhelper.dto.quiz.QuizAddQuestionDTO;
import pweb.examhelper.dto.quiz.QuizCreationDTO;
import pweb.examhelper.dto.quiz.QuizDTO;

public interface IQuizService {
    QuizDTO createQuiz(QuizCreationDTO quizCreationDTO, Long subjectId);
    QuizDTO getQuiz(Long subjectId, Long quizId);
    QuizDTO addQuestionToQuiz(Long subjectId, Long quizId, Long questionId);
    void deleteQuestionFromQuiz(Long subjectId, Long quizId, Long questionId);
    void deleteQuiz(Long subjectId, Long quizId);
}
