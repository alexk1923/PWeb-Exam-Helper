package pweb.examhelper.service;

import pweb.examhelper.dto.StudentDTO;
import pweb.examhelper.dto.StudentCreationDTO;
import pweb.examhelper.dto.StudentUpdateDTO;

import java.util.List;

public interface IStudentService {
    StudentDTO createStudent(StudentCreationDTO studentDTO);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudent(Long id);
    StudentDTO updateStudent(Long id, StudentUpdateDTO updateData);
    void deleteStudent(Long id);
}
