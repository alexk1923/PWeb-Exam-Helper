package pweb.examhelper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.constants.ErrorMessage;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.dto.student.StudentCreationDTO;
import pweb.examhelper.dto.student.StudentUpdateDTO;
import pweb.examhelper.entity.Student;
import pweb.examhelper.exception.ResourceAlreadyExists;
import pweb.examhelper.exception.ResourceNotFoundException;
import pweb.examhelper.mapper.StudentMapper;
import pweb.examhelper.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentService implements IStudentService{

    private StudentRepository studentRepository;

    @Override
    public StudentDTO createStudent(StudentCreationDTO studentCreationDTO) {
        Student student = StudentMapper.mapToStudent(studentCreationDTO);

        if(studentRepository.existsByUsername(student.getUsername())) {
            throw new ResourceAlreadyExists(ErrorMessage.USERNAME_CONFLICT);
        }

        if(studentRepository.existsByEmail(student.getEmail())) {
            throw new ResourceAlreadyExists(ErrorMessage.EMAIL_CONFLICT);
        }

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
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.RESOURCE_NOT_FOUND));
        return StudentMapper.mapToStudentDTO(student);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentUpdateDTO updateData) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.UPDATE_NOT_FOUND));
        if(updateData.getFirstName() != null && updateData.getFirstName().length() > 0) {
            student.setFirstName(updateData.getFirstName());
        }

        if(updateData.getLastName() != null && updateData.getLastName().length() > 0) {
            student.setLastName(updateData.getLastName());
        }

        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.DELETE_NOT_FOUND));
        studentRepository.delete(student);
    }
}
