package pweb.examhelper.mapper;
import pweb.examhelper.dto.group.GroupDTO;
import pweb.examhelper.dto.group.GroupDTOCreation;
import pweb.examhelper.dto.group.GroupStudentDTO;
import pweb.examhelper.dto.group.MemberInfoDTO;
import pweb.examhelper.entity.Group;
import pweb.examhelper.entity.GroupStudent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupMapper {
    public static Group mapToGroup(GroupDTO groupDTO) {
        Set<GroupStudent> groupStudentSet = new HashSet<>();
        return new Group(groupDTO.getId(), groupDTO.getName(), groupStudentSet);

    }

    public static Group mapToGroup(GroupDTOCreation groupDTO) {
        Set<GroupStudent> groupStudentSet = new HashSet<>();
        return new Group(groupDTO.getId(), groupDTO.getName(), groupStudentSet);
    }

    public static GroupDTO mapToGroupDTO(Group group) {
        Set<MemberInfoDTO> groupStudentDTOSet = new HashSet<>();
        if(group.getGroupStudents() != null && group.getGroupStudents().size() > 0) {
            groupStudentDTOSet =  group.getGroupStudents().stream().map(g -> new MemberInfoDTO(g.getStudent().getId(), g.getStudent().getUsername())).collect(Collectors.toSet());
        }


        return new GroupDTO(group.getId(), group.getName(), groupStudentDTOSet,
                groupStudentDTOSet.size());
    }
}
