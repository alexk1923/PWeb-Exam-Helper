package pweb.examhelper.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pweb.examhelper.dto.StudentDTO;
import pweb.examhelper.dto.StudentCreationDTO;
import pweb.examhelper.dto.StudentUpdateDTO;
import pweb.examhelper.service.StudentService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentCreationDTO studentCreationDTO) {
        StudentDTO savedStudent = studentService.createStudent(studentCreationDTO);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentsList = studentService.getAllStudents();
        return new ResponseEntity<>(studentsList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") Long id) {
        StudentDTO studentDTO = studentService.getStudent(id);
        return new ResponseEntity <>(studentDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Long id,
                                                    @RequestBody StudentUpdateDTO updatedStudentDTO) {
        StudentDTO afterUpdateStudentDTO = studentService.updateStudent(id, updatedStudentDTO);
        return new ResponseEntity<>(afterUpdateStudentDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Employee Deleted", HttpStatus.OK);
    }

}
