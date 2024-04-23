package pweb.examhelper.controller;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pweb.examhelper.dto.auth.LoginDTO;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.dto.student.StudentCreationDTO;
import pweb.examhelper.dto.student.StudentUpdateDTO;
import pweb.examhelper.service.StudentService;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;


    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentsList = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentsList);
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") Long id) {
        StudentDTO studentDTO = studentService.getStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Long id,
                                                    @Valid @RequestBody StudentUpdateDTO updatedStudentDTO) {
        StudentDTO afterUpdateStudentDTO = studentService.updateStudent(id, updatedStudentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(afterUpdateStudentDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
