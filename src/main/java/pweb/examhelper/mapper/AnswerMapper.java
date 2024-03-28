package pweb.examhelper.mapper;

import pweb.examhelper.dto.question.AnswerDTO;
import pweb.examhelper.dto.question.AnswerDTOCreation;
import pweb.examhelper.entity.Answer;

public class AnswerMapper {

    public static Answer mapToAnswer(AnswerDTOCreation answerDTO) {
        return new Answer(answerDTO.getText(), answerDTO.getIsCorrect());
    }

    public static Answer mapToAnswer(AnswerDTO answerDTO) {
        return new Answer(answerDTO.getText(), answerDTO.getIsCorrect());
    }


    public static AnswerDTO mapToAnswerDTO(Answer answer) {
        return new AnswerDTO(answer.getId(), answer.getText(), answer.getIsCorrect());
    }
}
