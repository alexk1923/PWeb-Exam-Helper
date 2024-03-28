package pweb.examhelper.mapper;

import pweb.examhelper.dto.group.GroupInfoDTO;
import pweb.examhelper.dto.group.GroupStudentDTO;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.dto.student.StudentCreationDTO;
import pweb.examhelper.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    public static StudentDTO mapToStudentDTO(Student student) {

        List<GroupInfoDTO> groupStudentDTOList;
        if(student.getGroups() != null && student.getGroups().size() > 0) {
            groupStudentDTOList = student.getGroups().stream().map(g -> new GroupInfoDTO(g.getGroup().getId(), g.getGroup().getName())).collect(Collectors.toList());
        } else {
            groupStudentDTOList = new ArrayList<>();
        }

        return new StudentDTO(student.getId(), student.getUsername(), student.getFirstName(), student.getLastName(),
                student.getEmail(), groupStudentDTOList);
    }

    public static Student mapToStudent(StudentCreationDTO studentCreationDTO) {
        return new Student(studentCreationDTO.getUsername(), studentCreationDTO.getFirstName(), studentCreationDTO.getLastName(), studentCreationDTO.getEmail());
    }

    public static Student mapToStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getUsername(), studentDTO.getFirstName(),
                studentDTO.getLastName(), studentDTO.getEmail());
    }
}
