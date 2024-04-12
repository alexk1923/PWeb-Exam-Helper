package pweb.examhelper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.constants.ErrorMessage;
import pweb.examhelper.dto.quiz.QuestionGetDTO;
import pweb.examhelper.dto.quiz.QuizAddQuestionDTO;
import pweb.examhelper.dto.quiz.QuizCreationDTO;
import pweb.examhelper.dto.quiz.QuizDTO;
import pweb.examhelper.entity.Question;
import pweb.examhelper.entity.Quiz;
import pweb.examhelper.entity.QuizQuestion;
import pweb.examhelper.entity.Subject;
import pweb.examhelper.exception.DatabaseUpdateException;
import pweb.examhelper.exception.ResourceNotFoundException;
import pweb.examhelper.logger.LoggingController;
import pweb.examhelper.mapper.QuizMapper;
import pweb.examhelper.repository.QuestionRepository;
import pweb.examhelper.repository.SubjectRepository;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class QuizService implements IQuizService{

    SubjectRepository subjectRepository;
    QuestionRepository questionRepository;

    @Override
    public QuizDTO createQuiz(QuizCreationDTO quizCreationDTO, Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SUBJECT_NOT_FOUND));
        Quiz newQuiz = new Quiz(quizCreationDTO.getTitle(), new ArrayList<>(), subject);
        subject.getQuizList().add(newQuiz);
        Subject updatedSubject = subjectRepository.save(subject);

        for(Quiz q : updatedSubject.getQuizList()) {
            if(q.getTitle().equals(quizCreationDTO.getTitle())) {
                return QuizMapper.mapToQuizDTO(q);
            }
        }

        throw new ResourceNotFoundException(ErrorMessage.QUIZ_NOT_FOUND);
    }

    @Override
    public QuizDTO getQuiz(Long subjectId, Long quizId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SUBJECT_NOT_FOUND));
        for(Quiz quiz : subject.getQuizList()) {
            if(quiz.getId().equals(quizId)) {
                return QuizMapper.mapToQuizDTO(quiz);
            }
        }

        throw new ResourceNotFoundException(ErrorMessage.QUIZ_NOT_FOUND);
    }


    @Override
    public QuizDTO addQuestionToQuiz(Long subjectId, Long quizId, Long questionId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SUBJECT_NOT_FOUND));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.QUESTION_NOT_FOUND));
        Quiz foundQuiz = null;

        for(Quiz quiz : subject.getQuizList()) {
            if(quiz.getId().equals(quizId)) {
                foundQuiz = quiz;
                quiz.getQuestionList().add(new QuizQuestion(question, quiz));
            }
        }

        if(foundQuiz == null) {
            throw new DatabaseUpdateException(ErrorMessage.QUIZ_NOT_FOUND);
        }
        subjectRepository.save(subject);
        return QuizMapper.mapToQuizDTO(foundQuiz);
    }

    @Override
    public void deleteQuestionFromQuiz(Long subjectId, Long quizId, Long questionId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SUBJECT_NOT_FOUND));
        boolean validQuizId = false;
        for(Quiz q : subject.getQuizList()) {
            if(q.getId().equals(quizId)) {
                validQuizId = q.getQuestionList().removeIf(question -> question.getId().equals(questionId));
                break;
            }
        }

        LoggingController.getLogger().info("validQuiz is " + validQuizId);

        if(!validQuizId) {
            throw new ResourceNotFoundException(ErrorMessage.QUIZ_NOT_FOUND);
        }
        subjectRepository.save(subject);
    }

    @Override
    public void deleteQuiz(Long subjectId, Long quizId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SUBJECT_NOT_FOUND));
        boolean validQuiz = subject.getQuizList().removeIf(q -> q.getId().equals(quizId));
        if(!validQuiz) {
            throw new ResourceNotFoundException(ErrorMessage.QUIZ_NOT_FOUND);
        }
        subjectRepository.save(subject);
    }
}
