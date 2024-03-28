package pweb.examhelper.service;

import pweb.examhelper.dto.group.GroupDTO;
import pweb.examhelper.dto.group.GroupCreationDTO;
import pweb.examhelper.dto.group.GroupStudentAddDTO;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.enums.Role;

public interface IGroupService {
    GroupDTO createGroup(GroupCreationDTO groupCreationDTO);
    GroupDTO getGroup(Long id);
    void deleteGroup(Long id);
    void changeGroupName(String newName, Long id);
    StudentDTO addStudentToGroup(GroupStudentAddDTO addStudentDTO, Long id);
    void removeStudentFromGroup(Long studentId, Long groupId);
    StudentDTO changeStudentGroupRole(Role newRole, Long studentId, Long groupId);

}
