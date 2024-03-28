package pweb.examhelper.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.dto.group.GroupDTO;
import pweb.examhelper.dto.group.GroupCreationDTO;
import pweb.examhelper.dto.group.GroupStudentAddDTO;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.entity.Group;
import pweb.examhelper.entity.GroupStudent;
import pweb.examhelper.entity.Student;
import pweb.examhelper.enums.Role;
import pweb.examhelper.logger.LoggingController;
import pweb.examhelper.mapper.GroupMapper;
import pweb.examhelper.mapper.StudentMapper;
import pweb.examhelper.repository.GroupRepository;
import pweb.examhelper.repository.StudentRepository;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GroupService implements IGroupService{

    private GroupRepository groupRepository;
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public GroupDTO createGroup(GroupCreationDTO groupCreationDTO) {

        Group newGroup = GroupMapper.mapToGroup(groupCreationDTO);
        if(newGroup != null) {
            LoggingController.getLogger().info(newGroup.getGroupStudents().toString());
            LoggingController.getLogger().info("newGroup nu este null");


        }
        Student defaultAdmin = studentRepository.findById(groupCreationDTO.getDefaultAdminId()).orElseThrow(() -> new RuntimeException());
        newGroup.getGroupStudents().add(new GroupStudent(newGroup,defaultAdmin, Role.ADMIN));
        Group savedGroup = groupRepository.save(newGroup);
        return GroupMapper.mapToGroupDTO(savedGroup);
    }

    @Override
    public GroupDTO getGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return GroupMapper.mapToGroupDTO(group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public void changeGroupName(String newName, Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException());
        group.setName(newName);
        Group updatedGroup = groupRepository.save(group);
    }

    @Override
    public StudentDTO addStudentToGroup(GroupStudentAddDTO addStudentDTO, Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException());
        Student student = studentRepository.findById(addStudentDTO.getId()).orElseThrow(() -> new RuntimeException());

        group.getGroupStudents().add(new GroupStudent(group, student, Role.valueOf(addStudentDTO.getRole()) ));
        Group updatedGroup = groupRepository.save(group);


        return StudentMapper.mapToStudentDTO(student);
    }

    @Override
    public StudentDTO changeStudentGroupRole(Role newRole, Long studentId, Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException());

        // Change role of the student with given id
        group.getGroupStudents().stream().forEach(groupStudent -> {
            Long currentId = groupStudent.getStudent().getId();
            LoggingController.getLogger().info("currentId is " + currentId + " vs " + studentId);
            if(groupStudent.getStudent().getId().equals(studentId)) {
                LoggingController.getLogger().info("matches an id of " + studentId);
                groupStudent.setRole(newRole);
            }
            });

        for(GroupStudent gs: group.getGroupStudents()) {
            LoggingController.getLogger().info(gs.getStudent().getUsername() + " " + gs.getRole());
        }

        Group updatedGroup = groupRepository.save(group);
        Student updatedStudent = null;

        for (GroupStudent ge : updatedGroup.getGroupStudents())
        {
            if (ge.getStudent().getId().equals(studentId))
            {
                updatedStudent = ge.getStudent();
            }
        }
        return StudentMapper.mapToStudentDTO(updatedStudent);
    }

    @Override
    public void removeStudentFromGroup(Long studentId, Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException());
         group.getGroupStudents().removeIf(groupStudent -> groupStudent.getStudent().getId().equals(studentId));

         for(GroupStudent gs : group.getGroupStudents()) {
             LoggingController.getLogger().info(gs.getStudent().getUsername() + " " + gs.getRole());
         }

        Group updatedGroup = groupRepository.save(group);

        Student updatedStudent = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException());
    }
}
