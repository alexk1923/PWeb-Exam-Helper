package pweb.examhelper.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pweb.examhelper.enums.Role;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "group_student")
@Getter
@Setter
public class GroupStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @NonNull  private Group group;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @NonNull private Student student;

    @Column(name = "role")
    @NonNull private Role role;


}