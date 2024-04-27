package pweb.examhelper.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pweb.examhelper.constants.ErrorMessage;
import pweb.examhelper.dto.question.*;
import pweb.examhelper.entity.Answer;
import pweb.examhelper.entity.Question;
import pweb.examhelper.exception.ResourceNotFoundException;
import pweb.examhelper.logger.LoggingController;
import pweb.examhelper.mapper.AnswerMapper;
import pweb.examhelper.mapper.QuestionMapper;
import pweb.examhelper.repository.AnswerRepository;
import pweb.examhelper.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QuestionService implements IQuestionService{

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    @Override
    public QuestionDTO createQuestion(QuestionCreationDTO questionDTO) {
        Question q = QuestionMapper.mapToQuestion(questionDTO);

        List<Answer> newAnswers = new ArrayList<>();
        for(Answer a : q.getAnswers()) {
            Answer newA = new Answer(a.getText(), a.getIsCorrect());
            newA.setQuestion(q);
            newAnswers.add(newA);
        }

        q.setAnswers(newAnswers);
        Question savedQuestion = questionRepository.save(q);
        return QuestionMapper.mapToQuestionDTO(savedQuestion);
    }

    @Override
    public Page<QuestionDTO> getAllQuestions(Pageable pageable) {
        Page<Question> questions = questionRepository.findAll(pageable);
        return questions.map(QuestionMapper::mapToQuestionDTO);
    }

    @Override
    public QuestionDTO getQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.QUESTION_NOT_FOUND));
        return QuestionMapper.mapToQuestionDTO(question);
    }


    @Override
    @Transactional
    public QuestionDTO updateQuestion(Long id, QuestionUpdateDTO updateData) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.QUESTION_NOT_FOUND));

        // Update text if not empty
        if(updateData.getText() != null && updateData.getText().length() > 0) {
            question.setText(updateData.getText());
        }

        List<Answer> newAnswers = new ArrayList<>();

        // Delete old answers
        for(Answer existingAnswer : question.getAnswers()) {
            boolean isPresent = false;
            for(AnswerDTOCreation newAnswer : updateData.getAnswers()) {
                // Check if an existing answer is present in the new request
                if(newAnswer.getText().equals(existingAnswer.getText()) && newAnswer.getIsCorrect().equals(existingAnswer.getIsCorrect())) {
                    isPresent = true;
                    break;
                }
            }

            if(!isPresent) {
                // Delete invalid answers
                answerRepository.deleteById(existingAnswer.getId());
            }
        }

        for(AnswerDTOCreation aDTO : updateData.getAnswers()) {
            Answer a = AnswerMapper.mapToAnswer(aDTO);
            a.setQuestion(question);
            newAnswers.add(a);
        }

        question.setAnswers(newAnswers);
        Question afterUpdateQuestion = questionRepository.save(question);
        return QuestionMapper.mapToQuestionDTO(afterUpdateQuestion);
    }

    @Override
    public void deleteQuestion(Long id) {
        Question deletedQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.QUESTION_NOT_FOUND));
        questionRepository.delete(deletedQuestion);
    }
}
