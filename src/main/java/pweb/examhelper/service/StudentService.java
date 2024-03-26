package pweb.examhelper.service;

import lombok.AllArgsConstructor;
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
        return studentsList.stream().map(StudentMapper::mapToStudentDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        return StudentMapper.mapToStudentDTO(student);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO updateData) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        if(updateData.getFirstName().length() > 0) {
            student.setFirstName(updateData.getFirstName());
        }

        if(updateData.getLastName().length() > 0) {
            student.setLastName(updateData.getLastName());
        }

        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        studentRepository.delete(student);
    }
}
