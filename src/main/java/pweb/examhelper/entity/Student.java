package pweb.examhelper.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(unique=true)
    @NonNull private String username;
    @NonNull private String firstName;
    @NonNull private String lastName;

    @Column(unique=true)
    @NonNull private String email;

    @OneToMany(mappedBy = "student")
    private List<GroupStudent> groups;
}
