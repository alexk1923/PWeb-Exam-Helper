package pweb.examhelper.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.dto.StudentDTO;
import pweb.examhelper.entity.Student;
import pweb.examhelper.mapper.StudentMapper;
import pweb.examhelper.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentService implements IStudentService{

    private StudentRepository studentRepository;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.mapToStudent(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDTO(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> studentsList = studentRepository.findAll();
        return studentsList.stream().map((s) -> StudentMapper.mapToStudentDTO(s)).collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return StudentMapper.mapToStudentDTO(student);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO updateData) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException());
        student.setFirstName(updateData.getFirstName());
        student.setLastName(updateData.getLastName());
//      student.setUsername(updateData.getUsername());

        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException());
        studentRepository.delete(student);
    }
}
