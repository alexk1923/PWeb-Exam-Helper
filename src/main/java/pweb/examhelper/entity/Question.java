package pweb.examhelper.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull private String text;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @NonNull private List<Answer> answers;
}
