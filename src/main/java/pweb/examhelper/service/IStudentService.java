package pweb.examhelper.service;

import pweb.examhelper.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    StudentDTO createStudent(StudentDTO studentDTO);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudent(Long id);
    StudentDTO updateStudent(Long id, StudentDTO updateData);
    void deleteStudent(Long id);
}
