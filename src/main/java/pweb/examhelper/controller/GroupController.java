package pweb.examhelper.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pweb.examhelper.dto.group.*;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.enums.Role;
import pweb.examhelper.response.JsonResponse;
import pweb.examhelper.service.GroupService;

@AllArgsConstructor
@RequestMapping("/api/groups")
@RestController
public class GroupController {
    private GroupService groupService;

    @GetMapping("{id}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable Long id) {
        GroupDTO groupDTO = groupService.getGroup(id);
        return new ResponseEntity<>(groupDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupCreationDTO groupCreationDTO) {

        GroupDTO savedGroup = groupService.createGroup(groupCreationDTO);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<JsonResponse> changeGroupName(@RequestBody GroupUpdateNameDTO groupUpdateNameDTO,
                                                        @PathVariable Long id) {
        groupService.changeGroupName(groupUpdateNameDTO.getNewName(), id);
        return ResponseEntity.ok(new JsonResponse("Changed group name with id " + id + " to "
                + groupUpdateNameDTO.getNewName()));
    }

    @PostMapping("/{groupId}/members")
    public ResponseEntity<StudentDTO> addStudentToGroup(@PathVariable("groupId") Long groupId,
                                                        @RequestBody GroupStudentAddDTO addStudentDTO) {
        if(EnumUtils.isValidEnum(Role.class, addStudentDTO.getRole())) {
            StudentDTO savedStudentGroup = groupService.addStudentToGroup(addStudentDTO, groupId);
            return new ResponseEntity<>(savedStudentGroup, HttpStatus.OK);
        } else {
            throw new RuntimeException();
        }

    }

    @PutMapping("{groupId}/members/")
    public ResponseEntity<JsonResponse> changeStudentRole(@RequestBody GroupStudentUpdateRoleDTO updateRoleDTO,
                                                           Long groupId) {
        if(EnumUtils.isValidEnum(Role.class, updateRoleDTO.getNewRole())) {
            StudentDTO student =  groupService.changeStudentGroupRole(Role.valueOf(updateRoleDTO.getNewRole()), updateRoleDTO.getStudentId() , groupId);
            return ResponseEntity.ok(new JsonResponse("Changed role for "  + updateRoleDTO.getStudentId() + "to " + updateRoleDTO.getNewRole()));
        } else {
            throw new RuntimeException();
        }

    }

    @DeleteMapping("{groupId}/members/{studentId}")
    public void removeStudentFromGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        groupService.removeStudentFromGroup(studentId, groupId);
    }



}
