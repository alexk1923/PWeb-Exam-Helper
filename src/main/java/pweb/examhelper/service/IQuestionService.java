package pweb.examhelper.service;

import pweb.examhelper.dto.question.QuestionCreationDTO;
import pweb.examhelper.dto.question.QuestionDTO;
import pweb.examhelper.dto.question.QuestionUpdateDTO;

import java.util.List;

public interface IQuestionService {
    QuestionDTO createQuestion(QuestionCreationDTO questionDTO);
    List<QuestionDTO> getAllQuestions();
    QuestionDTO getQuestion(Long id);
    QuestionDTO updateQuestion(Long id, QuestionUpdateDTO questionDTO);
    void deleteQuestion(Long id);
}
