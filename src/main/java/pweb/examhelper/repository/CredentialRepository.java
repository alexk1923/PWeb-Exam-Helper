package pweb.examhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pweb.examhelper.entity.Credential;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Optional<Credential> findByStudent_Username(String username);
}
