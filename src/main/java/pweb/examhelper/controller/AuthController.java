package pweb.examhelper.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pweb.examhelper.dto.auth.LoginDTO;
import pweb.examhelper.dto.auth.LoginResponse;
import pweb.examhelper.dto.student.StudentCreationDTO;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.logger.LoggingController;
import pweb.examhelper.response.JsonResponse;
import pweb.examhelper.service.StudentService;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private StudentService studentService;

    @PostMapping("/register")
    public StudentDTO register(@RequestBody StudentCreationDTO studentCreationDTO) {
        StudentDTO savedStudentDTO = studentService.registerStudent(studentCreationDTO);
        return savedStudentDTO;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = studentService.login(loginDTO);
        return ResponseEntity.ok(loginResponse);
//        LoggingController.getLogger().info("sunt in login");
//        return ResponseEntity.ok("");
    }
}
