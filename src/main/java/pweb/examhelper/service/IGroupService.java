package pweb.examhelper.service;

import pweb.examhelper.dto.GroupDTO;
import pweb.examhelper.dto.StudentDTO;
import pweb.examhelper.enums.Role;

public interface IGroupService {
    GroupDTO createGroup(GroupDTO groupDTO, Long defaultAdminId);
    GroupDTO getGroup(Long id);
    void deleteGroup(Long id);
    void changeGroupName(String newName, Long id);
    StudentDTO addStudentToGroup(StudentDTO studentDTO, Long id);
    StudentDTO removeStudentFromGroup(Long studentId, Long groupId);
    void changeStudentGroupRole(Role newRole, Long studentId, Long groupId);

}
