package pweb.examhelper.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import pweb.examhelper.enums.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;

    @OneToMany
    private Map<Student, Role> studentsMap = new HashMap<>();

}
