package pweb.examhelper.mapper;

import pweb.examhelper.dto.QuestionDTO;
import pweb.examhelper.entity.Question;

import java.util.stream.Collectors;

public class QuestionMapper {
    public static QuestionDTO mapToQuestionDTO(Question question) {
        return new QuestionDTO(question.getId(), question.getText(), question.getAnswers().stream()
                .map(a -> AnswerMapper.mapToAnswerDTO(a)).collect(Collectors.toList()));
    }

    public static Question mapToQuestion(QuestionDTO questionDTO) {
        return new Question(questionDTO.getId(), questionDTO.getText(), questionDTO.getAnswers().stream()
                .map(a -> AnswerMapper.mapToAnswer(a)).collect(Collectors.toList()));
    }
}
