package pweb.examhelper.mapper;

import pweb.examhelper.dto.GroupDTO;
import pweb.examhelper.dto.GroupStudentDTO;
import pweb.examhelper.dto.StudentDTO;
import pweb.examhelper.entity.Group;
import pweb.examhelper.entity.GroupStudent;
import pweb.examhelper.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    public static StudentDTO mapToStudentDTO(Student student) {

        List<GroupStudentDTO> groupStudentDTOList;
        if(student.getGroups() != null && student.getGroups().size() > 0) {
            groupStudentDTOList = student.getGroups().stream().map(GroupStudentMapper::mapToGroupStudentDTO).collect(Collectors.toList());
        } else {
            groupStudentDTOList = new ArrayList<>();
        }
        return new StudentDTO(student.getId(), student.getUsername(), student.getFirstName(), student.getLastName(),
                student.getEmail(), groupStudentDTOList);
    }

    public static Student mapToStudent(StudentDTO studentDTO) {


        return new Student(studentDTO.getUsername(), studentDTO.getFirstName(),
                studentDTO.getLastName(), studentDTO.getEmail());
    }
}
