package pweb.examhelper.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pweb.examhelper.entity.Credential;
import pweb.examhelper.repository.CredentialRepository;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialRepository.findByStudent_Username(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

}
