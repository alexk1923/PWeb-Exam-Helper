package pweb.examhelper.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<GroupStudent> groups;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Credential credential;
}
