package pweb.examhelper.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class QuizCreationDTO {
    @NonNull
    private String title;
}
