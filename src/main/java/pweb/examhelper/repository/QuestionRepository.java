package pweb.examhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pweb.examhelper.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
