package pweb.examhelper.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @NonNull
    private Question question;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @NonNull
    private Quiz quiz;

}
