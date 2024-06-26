package pweb.examhelper.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pweb.examhelper.entity.Credential;
import pweb.examhelper.logger.LoggingController;
import pweb.examhelper.repository.CredentialRepository;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = credentialRepository.findByStudent_Username(username).orElseThrow();
        LoggingController.getLogger().info(credential.getUsername());
        LoggingController.getLogger().info(credential.getPassword());

        return credentialRepository.findByStudent_Username(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

}
