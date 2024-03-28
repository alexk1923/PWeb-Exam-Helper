package pweb.examhelper.mapper;

import pweb.examhelper.dto.question.QuestionCreationDTO;
import pweb.examhelper.dto.question.QuestionDTO;
import pweb.examhelper.entity.Question;

import java.util.stream.Collectors;

public class QuestionMapper {
    public static QuestionDTO mapToQuestionDTO(Question question) {
        return new QuestionDTO(question.getId(), question.getText(), question.getAnswers().stream()
                .map(AnswerMapper::mapToAnswerDTO).collect(Collectors.toList()));
    }

    public static Question mapToQuestion(QuestionCreationDTO questionDTO) {
        return new Question(questionDTO.getText(), questionDTO.getAnswers().stream()
                .map(AnswerMapper::mapToAnswer).collect(Collectors.toList()));
    }

    public static Question mapToQuestion(QuestionDTO questionDTO) {
        return new Question(questionDTO.getId(), questionDTO.getText(), questionDTO.getAnswers().stream()
                .map(AnswerMapper::mapToAnswer).collect(Collectors.toList()));
    }
}
