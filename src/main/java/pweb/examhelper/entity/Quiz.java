package pweb.examhelper.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @NonNull
    private List<QuizQuestion> questionList;

    @ManyToOne
    @JoinColumn(name = "subjectId", referencedColumnName="id")
    @NonNull
    private Subject subject;
}
