package pweb.examhelper.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pweb.examhelper.constants.ErrorMessage;
import pweb.examhelper.dto.auth.JwtTokenUtil;
import pweb.examhelper.dto.auth.LoginDTO;
import pweb.examhelper.dto.auth.LoginResponse;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.dto.student.StudentCreationDTO;
import pweb.examhelper.dto.student.StudentUpdateDTO;
import pweb.examhelper.entity.Credential;
import pweb.examhelper.entity.Student;
import pweb.examhelper.exception.ResourceAlreadyExists;
import pweb.examhelper.exception.ResourceNotFoundException;
import pweb.examhelper.logger.LoggingController;
import pweb.examhelper.mapper.StudentMapper;
import pweb.examhelper.repository.CredentialRepository;
import pweb.examhelper.repository.StudentRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentService implements IStudentService {

    private StudentRepository studentRepository;
    private CredentialRepository credentialRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtUtil;
    private AuthUserDetailsService authUserDetailsService;


    @Override
    public StudentDTO registerStudent(StudentCreationDTO studentCreationDTO) {

        // Encrypt the password
        String encryptedPassword = passwordEncoder.encode(studentCreationDTO.getPassword());
        Student student = StudentMapper.mapToStudent(studentCreationDTO);

        if(studentRepository.existsByUsername(student.getUsername())) {
            throw new ResourceAlreadyExists(ErrorMessage.USERNAME_CONFLICT);
        }

        if(studentRepository.existsByEmail(student.getEmail())) {
            throw new ResourceAlreadyExists(ErrorMessage.EMAIL_CONFLICT);
        }

        Student savedStudent = studentRepository.save(student);

        // Create and save the credential
        Credential credential = new Credential(savedStudent, encryptedPassword,
                studentCreationDTO.getStandardRole());
        credentialRepository.save(credential);

        return StudentMapper.mapToStudentDTO(savedStudent);
    }

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        // Authenticate the student
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate a JWT
        UserDetails userDetails = authUserDetailsService.loadUserByUsername(loginDTO.getUsername());
        return new LoginResponse(userDetails.getUsername(), jwtUtil.generateToken(userDetails));
    }


    @Override
    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        Page<Student> studentsList = studentRepository.findAll(pageable);
        return studentsList.map(StudentMapper::mapToStudentDTO);
    }

    @Override
    public StudentDTO getStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.RESOURCE_NOT_FOUND));
        return StudentMapper.mapToStudentDTO(student);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentUpdateDTO updateData) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.UPDATE_NOT_FOUND));
        if(updateData.getFirstName() != null && updateData.getFirstName().length() > 0) {
            student.setFirstName(updateData.getFirstName());
        }

        if(updateData.getLastName() != null && updateData.getLastName().length() > 0) {
            student.setLastName(updateData.getLastName());
        }

        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.DELETE_NOT_FOUND));
        studentRepository.delete(student);
    }

}
