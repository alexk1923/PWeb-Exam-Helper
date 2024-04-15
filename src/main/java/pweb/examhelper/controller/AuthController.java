package pweb.examhelper.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pweb.examhelper.dto.auth.LoginDTO;
import pweb.examhelper.dto.student.StudentCreationDTO;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.service.StudentService;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private StudentService studentService;

    @PostMapping("/register")
    public StudentDTO register(@RequestBody StudentCreationDTO studentCreationDTO) {
        System.out.println("aolo");
        StudentDTO savedStudentDTO = studentService.registerStudent(studentCreationDTO);
        return savedStudentDTO;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = studentService.login(loginDTO);
        return ResponseEntity.ok(token);
    }
}
