package pweb.examhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pweb.examhelper.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
