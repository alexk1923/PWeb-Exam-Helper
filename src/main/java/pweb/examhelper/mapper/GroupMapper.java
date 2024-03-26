package pweb.examhelper.mapper;
import pweb.examhelper.dto.GroupDTO;
import pweb.examhelper.dto.GroupStudentDTO;
import pweb.examhelper.entity.Group;
import pweb.examhelper.entity.GroupStudent;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupMapper {
    public static Group mapToGroup(GroupDTO groupDTO) {
        Set<GroupStudent> groupStudentSet = Collections.emptySet();
        if(groupDTO.getGroupStudents() != null && groupDTO.getGroupStudents().size() > 0) {
            groupStudentSet =  groupDTO.getGroupStudents().stream().map(GroupStudentMapper::mapToGroupStudent).collect(Collectors.toSet());
        }

        return new Group(groupDTO.getId(), groupDTO.getName(), groupStudentSet);

    }

    public static GroupDTO mapToGroupDTO(Group group) {
        Set<GroupStudentDTO> groupStudentDTOSet = Collections.emptySet();
        if(group.getGroupStudents() != null && group.getGroupStudents().size() > 0) {
            groupStudentDTOSet =  group.getGroupStudents().stream().map(GroupStudentMapper::mapToGroupStudentDTO).collect(Collectors.toSet());
        }


        return new GroupDTO(group.getId(), group.getName(), groupStudentDTOSet,
                groupStudentDTOSet.size());
    }
}
