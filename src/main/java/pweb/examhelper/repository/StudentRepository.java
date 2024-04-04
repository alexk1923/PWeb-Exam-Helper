package pweb.examhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pweb.examhelper.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
