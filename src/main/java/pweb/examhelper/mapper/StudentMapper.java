package pweb.examhelper.mapper;

import pweb.examhelper.dto.StudentDTO;
import pweb.examhelper.entity.Student;

public class StudentMapper {
    public static StudentDTO mapToStudentDTO(Student student) {
        return new StudentDTO(student.getId(), student.getUsername(), student.getFirstName(), student.getLastName(),
                student.getEmail());
    }

    public static Student mapToStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getUsername(), studentDTO.getFirstName(),
                studentDTO.getLastName(), studentDTO.getEmail());
    }
}
