package pweb.examhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pweb.examhelper.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
