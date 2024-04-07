package pweb.examhelper.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pweb.examhelper.constants.ErrorMessage;
import pweb.examhelper.dto.group.*;
import pweb.examhelper.dto.student.StudentDTO;
import pweb.examhelper.enums.Role;
import pweb.examhelper.exception.BadRequestException;
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
    public ResponseEntity<GroupDTO> createGroup(@Valid @RequestBody GroupCreationDTO groupCreationDTO) {
        if(groupCreationDTO.getDefaultAdminId() == null) {
            throw new BadRequestException(ErrorMessage.NO_ADMIN_PROVIDED);
        }
        GroupDTO savedGroup = groupService.createGroup(groupCreationDTO);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<GroupDTO> changeGroupName(@Valid @RequestBody GroupUpdateNameDTO groupUpdateNameDTO,
                                                        @PathVariable Long id) {
        GroupDTO updatedGroupDTO = groupService.changeGroupName(groupUpdateNameDTO.getName(), id);
        return ResponseEntity.ok(updatedGroupDTO);
    }

    @PostMapping("/{groupId}/members")
    public ResponseEntity<StudentDTO> addStudentToGroup(@PathVariable("groupId") Long groupId,
                                                        @Valid @RequestBody GroupStudentAddDTO addStudentDTO) {
        if(EnumUtils.isValidEnum(Role.class, addStudentDTO.getRole())) {
            StudentDTO savedStudentGroup = groupService.addStudentToGroup(addStudentDTO, groupId);
            return new ResponseEntity<>(savedStudentGroup, HttpStatus.OK);
        } else {
            throw new BadRequestException(ErrorMessage.INVALID_ROLE);
        }

    }

    @PutMapping("{groupId}/members/")
    public ResponseEntity<JsonResponse> changeStudentRole(@Valid @RequestBody GroupStudentUpdateRoleDTO updateRoleDTO,
                                                           Long groupId) {
        if(EnumUtils.isValidEnum(Role.class, updateRoleDTO.getRole())) {
            StudentDTO student =  groupService.changeStudentGroupRole(Role.valueOf(updateRoleDTO.getRole()), updateRoleDTO.getStudentId() , groupId);
            return ResponseEntity.ok(new JsonResponse("Changed role for "  + updateRoleDTO.getStudentId() + "to " + updateRoleDTO.getRole()));
        } else {
            throw new BadRequestException(ErrorMessage.INVALID_ROLE);
        }

    }

    @DeleteMapping("{groupId}/members/{studentId}")
    public ResponseEntity<Void> removeStudentFromGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        groupService.removeStudentFromGroup(studentId, groupId);
        return ResponseEntity.noContent().build();
    }



}
