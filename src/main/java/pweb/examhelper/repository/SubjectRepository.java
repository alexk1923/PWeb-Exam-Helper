package pweb.examhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pweb.examhelper.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
