package pweb.examhelper.service;

import pweb.examhelper.dto.auth.LoginDTO;
import pweb.examhelper.dto.auth.LoginResponse;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.dto.student.StudentCreationDTO;
import pweb.examhelper.dto.student.StudentUpdateDTO;

import java.util.List;

public interface IStudentService {
    StudentDTO registerStudent(StudentCreationDTO studentDTO);
    LoginResponse login(LoginDTO loginDTO);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudent(Long id);
    StudentDTO updateStudent(Long id, StudentUpdateDTO updateData);
    void deleteStudent(Long id);
}
