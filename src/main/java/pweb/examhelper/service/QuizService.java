package pweb.examhelper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.dto.quiz.QuestionGetDTO;
import pweb.examhelper.dto.quiz.QuizAddQuestionDTO;
import pweb.examhelper.dto.quiz.QuizCreationDTO;
import pweb.examhelper.dto.quiz.QuizDTO;
import pweb.examhelper.entity.Question;
import pweb.examhelper.entity.Quiz;
import pweb.examhelper.entity.QuizQuestion;
import pweb.examhelper.entity.Subject;
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
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(RuntimeException::new);
        Quiz newQuiz = new Quiz(quizCreationDTO.getTitle(), new ArrayList<>(), subject);
        subject.getQuizList().add(newQuiz);
        Subject updatedSubject = subjectRepository.save(subject);
        for(Quiz q : updatedSubject.getQuizList()) {
            if(q.getTitle().equals(quizCreationDTO.getTitle())) {
                return QuizMapper.mapToQuizDTO(q);
            }
        }
        throw new RuntimeException();
    }

    @Override
    public QuizDTO getQuiz(Long subjectId, Long quizId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(RuntimeException::new);
        for(Quiz quiz : subject.getQuizList()) {
            if(quiz.getId().equals(quizId)) {
                return QuizMapper.mapToQuizDTO(quiz);
            }
        }
        throw new RuntimeException();
    }


    @Override
    public QuizDTO addQuestionToQuiz(Long subjectId, Long quizId, Long questionId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(RuntimeException::new);
        Question question = questionRepository.findById(questionId).orElseThrow(RuntimeException::new);
        Quiz quiz = null;

        for(Quiz q : subject.getQuizList()) {
            if(q.getId().equals(quizId)) {
                quiz = q;
                q.getQuestionList().add(new QuizQuestion(question, q));
            }
        }

        subjectRepository.save(subject);
        return QuizMapper.mapToQuizDTO(quiz);
    }

    @Override
    public void deleteQuestionFromQuiz(Long subjectId, Long quizId, Long questionId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(RuntimeException::new);
        for(Quiz q : subject.getQuizList()) {
            if(q.getId().equals(quizId)) {
                q.getQuestionList().removeIf(question -> question.getId().equals(questionId));
            }
        }

    }

    @Override
    public void deleteQuiz(Long subjectId, Long quizId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(RuntimeException::new);
        subject.getQuizList().removeIf(q -> q.getId().equals(quizId));
    }
}
