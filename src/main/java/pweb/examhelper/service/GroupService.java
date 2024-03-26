package pweb.examhelper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.dto.GroupDTO;
import pweb.examhelper.dto.StudentDTO;
import pweb.examhelper.entity.Group;
import pweb.examhelper.enums.Role;
import pweb.examhelper.mapper.GroupMapper;
import pweb.examhelper.repository.GroupRepository;

import javax.management.RuntimeErrorException;

@AllArgsConstructor
@Service
public class GroupService implements IGroupService{

    private GroupRepository groupRepository;

    @Override
    public GroupDTO createGroup(GroupDTO groupDTO, Long defaultAdminId) {

        Group savedGroup = groupRepository.save(GroupMapper.mapToGroup(groupDTO));
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
