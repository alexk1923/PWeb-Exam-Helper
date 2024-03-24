package pweb.examhelper.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private String username;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String email;

}
