package pweb.examhelper.service;

import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.dto.student.StudentCreationDTO;
import pweb.examhelper.dto.student.StudentUpdateDTO;

import java.util.List;

public interface IStudentService {
    StudentDTO createStudent(StudentCreationDTO studentDTO);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudent(Long id);
    StudentDTO updateStudent(Long id, StudentUpdateDTO updateData);
    void deleteStudent(Long id);
}
