package pweb.examhelper.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.constants.ErrorMessage;
import pweb.examhelper.dto.group.GroupDTO;
import pweb.examhelper.dto.group.GroupCreationDTO;
import pweb.examhelper.dto.group.GroupStudentAddDTO;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.entity.Group;
import pweb.examhelper.entity.GroupStudent;
import pweb.examhelper.entity.Student;
import pweb.examhelper.enums.Role;
import pweb.examhelper.exception.ResourceNotFoundException;
import pweb.examhelper.logger.LoggingController;
import pweb.examhelper.mapper.GroupMapper;
import pweb.examhelper.mapper.StudentMapper;
import pweb.examhelper.repository.GroupRepository;
import pweb.examhelper.repository.StudentRepository;

import java.util.concurrent.atomic.AtomicBoolean;


@AllArgsConstructor
@Service
public class GroupService implements IGroupService{

    private GroupRepository groupRepository;
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public GroupDTO createGroup(GroupCreationDTO groupCreationDTO) {

        Group newGroup = GroupMapper.mapToGroup(groupCreationDTO);
        Student defaultAdmin = studentRepository.findById(groupCreationDTO.getDefaultAdminId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.INVALID_DEFAULT_ADMIN));
        newGroup.getGroupStudents().add(new GroupStudent(newGroup,defaultAdmin, Role.ADMIN));
        Group savedGroup = groupRepository.save(newGroup);
        return GroupMapper.mapToGroupDTO(savedGroup);
    }

    @Override
    public GroupDTO getGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.RESOURCE_NOT_FOUND));
        return GroupMapper.mapToGroupDTO(group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.DELETE_NOT_FOUND));
        groupRepository.deleteById(id);
    }

    @Override
    public GroupDTO changeGroupName(String newName, Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.UPDATE_NOT_FOUND));
        group.setName(newName);
        Group updatedGroup = groupRepository.save(group);
        return GroupMapper.mapToGroupDTO(updatedGroup);
    }

    @Override
    public StudentDTO addStudentToGroup(GroupStudentAddDTO addStudentDTO, Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.RESOURCE_NOT_FOUND));
        Student student = studentRepository.findById(addStudentDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.STUDENT_ADD_NOT_FOUND));

        group.getGroupStudents().add(new GroupStudent(group, student, Role.valueOf(addStudentDTO.getRole()) ));
        Group updatedGroup = groupRepository.save(group);


        return StudentMapper.mapToStudentDTO(student);
    }

    @Override
    public StudentDTO changeStudentGroupRole(Role newRole, Long studentId, Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.RESOURCE_NOT_FOUND));

        // Change role of the student with given id
        AtomicBoolean validStudentId = new AtomicBoolean(false);
        group.getGroupStudents().forEach(groupStudent -> {
            Long currentId = groupStudent.getStudent().getId();

            if(groupStudent.getStudent().getId().equals(studentId)) {
                groupStudent.setRole(newRole);
                validStudentId.set(true);
            }
            });

        if(!validStudentId.get()) {
            throw new ResourceNotFoundException(ErrorMessage.UPDATE_NOT_FOUND);
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
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.RESOURCE_NOT_FOUND));
         boolean result = group.getGroupStudents().removeIf(groupStudent -> groupStudent.getStudent().getId()
                 .equals(studentId));

         if(!result) {
             throw new ResourceNotFoundException(ErrorMessage.STUDENT_DELETE_NOT_FOUND);
         }
         LoggingController.getLogger().info("result is " + result);

         for(GroupStudent gs : group.getGroupStudents()) {
             LoggingController.getLogger().info(gs.getStudent().getUsername() + " " + gs.getRole());
         }

         groupRepository.save(group);
    }
}
