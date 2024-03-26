package pweb.examhelper.mapper;

import pweb.examhelper.dto.GroupStudentDTO;
import pweb.examhelper.entity.Group;
import pweb.examhelper.entity.GroupStudent;
import pweb.examhelper.entity.Student;
import pweb.examhelper.service.GroupService;
import pweb.examhelper.service.StudentService;

public class GroupStudentMapper {

    private static GroupService groupService;
    private static StudentService studentService;

    public static GroupStudent mapToGroupStudent(GroupStudentDTO groupStudentDTO) {
        Group group = GroupMapper.mapToGroup(groupService.getGroup(groupStudentDTO.getGroupId()));
        Student student = StudentMapper.mapToStudent(studentService.getStudent(groupStudentDTO.getStudentId()));
        return new GroupStudent(groupStudentDTO.getGroupId(), group, student, groupStudentDTO.getRole());
    }

    public static GroupStudentDTO mapToGroupStudentDTO(GroupStudent groupStudent) {
        return new GroupStudentDTO(groupStudent.getId(), groupStudent.getGroup().getId(), groupStudent.getStudent().getId(), groupStudent.getRole());
    }
}
