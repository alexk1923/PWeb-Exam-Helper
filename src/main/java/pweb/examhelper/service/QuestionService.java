package pweb.examhelper.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.dto.AnswerDTO;
import pweb.examhelper.dto.QuestionDTO;
import pweb.examhelper.entity.Answer;
import pweb.examhelper.entity.Question;
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
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
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
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(QuestionMapper::mapToQuestionDTO).collect(Collectors.toList());
    }

    @Override
    public QuestionDTO getQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(RuntimeException::new);
        return QuestionMapper.mapToQuestionDTO(question);
    }


    @Override
    @Transactional
    public QuestionDTO updateQuestion(Long id, QuestionDTO updateData) {
        LoggingController.getLogger().info("Starting to update question with id " + id);
        Question question = questionRepository.findById(id).orElseThrow(RuntimeException::new);
        if(updateData.getText().length() > 0) {
            question.setText(updateData.getText());
        }

        List<Answer> newAnswers = new ArrayList<>();

        LoggingController.getLogger().info("Question from database has the following answers");
        for(Answer a : question.getAnswers()) {
            LoggingController.getLogger().info(a.getText() + ", isCorrect: "
                    + a.getIsCorrect());
        }

        // Delete old answers
        for(Answer existingAnswer : question.getAnswers()) {
            boolean isPresent = false;
            for(AnswerDTO newAnswer : updateData.getAnswers()) {
                if(newAnswer.getText().equals(existingAnswer.getText())) {
                    isPresent = true;
                    break;
                }
            }

            if(!isPresent) {
                LoggingController.getLogger().info("Answer" +
                        existingAnswer.getText() + ",id: " + existingAnswer.getId() +
                        " will be deleted" );
                answerRepository.deleteById(existingAnswer.getId());
            } else {
                LoggingController.getLogger().info("Keeping the answer, because it is present in the" +
                        "given array");
            }
        }


        for(AnswerDTO aDTO : updateData.getAnswers()) {
            Answer a = AnswerMapper.mapToAnswer(aDTO);

            boolean justUpdate = false;
            for(Answer existingAnswer : question.getAnswers()) {
                if(existingAnswer.getText().equals(a.getText())) {
                    existingAnswer.setIsCorrect(a.getIsCorrect());
                    answerRepository.save(existingAnswer);
                    justUpdate = true;
                    break;
                }
            }

            if(!justUpdate) {
                a.setQuestion(question);
                newAnswers.add(a);
            }
        }

        question.setAnswers(newAnswers);
        Question updatedQuestion = questionRepository.save(question);
        LoggingController.getLogger().info("DONE UPDATING QUESTION!");
        return QuestionMapper.mapToQuestionDTO(updatedQuestion);
    }

    @Override
    public void deleteQuestion(Long id) {
        Question deletedQuestion = questionRepository.findById(id).orElseThrow(RuntimeException::new);
        questionRepository.delete(deletedQuestion);
    }
}
