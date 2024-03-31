package pweb.examhelper.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QuizAddQuestionDTO {
    @NonNull
    private Long questionId;
}
