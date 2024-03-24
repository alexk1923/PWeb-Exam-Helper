package pweb.examhelper.service;

import pweb.examhelper.dto.QuestionDTO;
import pweb.examhelper.dto.StudentDTO;
import pweb.examhelper.entity.Question;

import java.util.List;

public interface IQuestionService {
    QuestionDTO createQuestion(QuestionDTO questionDTO);
    List<QuestionDTO> getAllQuestions();
    QuestionDTO getQuestion(Long id);
    QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO);
    void deleteQuestion(Long id);
}
