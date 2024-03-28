package pweb.examhelper.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pweb.examhelper.dto.group.GroupDTO;
import pweb.examhelper.dto.group.GroupDTOCreation;
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
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTOCreation groupDTOCreation) {

        GroupDTO savedGroup = groupService.createGroup(groupDTOCreation);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<JsonResponse> changeGroupName(@RequestBody String newName,
                                                        @PathVariable Long id) {
        groupService.changeGroupName(newName, id);
        return ResponseEntity.ok(new JsonResponse("Changed group name with id " + id + " to " + newName));
    }

    @PostMapping("{groupId}/members")
    public ResponseEntity<StudentDTO> addStudentToGroup(@RequestBody StudentDTO studentDTO,
                                                        @PathVariable Long groupId) {
        StudentDTO savedStudentGroup = groupService.addStudentToGroup(studentDTO, groupId);
        return new ResponseEntity<>(savedStudentGroup, HttpStatus.OK);
    }

    @PutMapping("{groupId}/members/")
    public ResponseEntity<JsonResponse> changeStudentRole(@RequestBody Role newRole,
                                                          @RequestBody Long studentId,
                                                           Long groupId) {
        groupService.changeStudentGroupRole(newRole, studentId, groupId);
        return ResponseEntity.ok(new JsonResponse("Changed role for "  + studentId));
    }

    @DeleteMapping("{groupId}/members/{studentId}")
    public void removeStudentFromGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        groupService.removeStudentFromGroup(studentId, groupId);
    }



}
