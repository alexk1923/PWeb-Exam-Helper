package pweb.examhelper.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.dto.group.GroupDTO;
import pweb.examhelper.dto.group.GroupDTOCreation;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.entity.Group;
import pweb.examhelper.entity.GroupStudent;
import pweb.examhelper.entity.Student;
import pweb.examhelper.enums.Role;
import pweb.examhelper.logger.LoggingController;
import pweb.examhelper.mapper.GroupMapper;
import pweb.examhelper.repository.GroupRepository;
import pweb.examhelper.repository.StudentRepository;

import java.util.logging.Logger;

@AllArgsConstructor
@Service
public class GroupService implements IGroupService{

    private GroupRepository groupRepository;
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public GroupDTO createGroup(GroupDTOCreation groupDTOCreation) {

        Group newGroup = GroupMapper.mapToGroup(groupDTOCreation);
        if(newGroup != null) {
            LoggingController.getLogger().info(newGroup.getGroupStudents().toString());
            LoggingController.getLogger().info("newGroup nu este null");


        }
        Student defaultAdmin = studentRepository.findById(groupDTOCreation.getDefaultAdminId()).orElseThrow(() -> new RuntimeException());
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
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException());;
        group.setName(newName);
        Group updatedGroup = groupRepository.save(group);
    }

    @Override
    public StudentDTO addStudentToGroup(StudentDTO studentDTO, Long id) {
        return null;
    }

    @Override
    public StudentDTO removeStudentFromGroup(Long studentId, Long groupId) {
        return null;
    }

    @Override
    public void changeStudentGroupRole(Role newRole, Long studentId, Long groupId) {

    }
}
