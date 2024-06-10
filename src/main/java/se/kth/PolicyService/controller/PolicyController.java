package se.kth.PolicyService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.PolicyService.model.*;
import se.kth.PolicyService.model.Attributes.Environment;
import se.kth.PolicyService.model.Attributes.Resource;
import se.kth.PolicyService.model.Attributes.Role;
import se.kth.PolicyService.model.Attributes.User;
import se.kth.PolicyService.service.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private RoleAssignmentService roleAssignmentService;

    @Autowired
    private PermissionAssignmentService permissionAssignmentService;

    @GetMapping("/assign-role")
    public String assignRole(@RequestParam String userId, @RequestParam String userName,
                             @RequestParam String userPosition, @RequestParam String userDepartment,
                             @RequestParam String envTime, @RequestParam String envLocation) {
        User user = new User(userId, userName, userPosition, userDepartment);
        Environment environment = new Environment(LocalTime.parse(envTime), envLocation);

        String roleName = roleAssignmentService.assignRole(user, environment);
        return roleName != null ? "Assigned role: " + roleName : "No role assigned";
    }

    @GetMapping("/assign-permissions")
    public List<String> assignPermissions(@RequestParam String roleName, @RequestParam String resourceId,
                                          @RequestParam String resourceType, @RequestParam String resourceOwner,
                                          @RequestParam String resourceCreationDate, @RequestParam String envTime,
                                          @RequestParam String envLocation) {
        Role role = new Role(roleName, List.of());
        Resource resource = new Resource(resourceId, resourceType, resourceOwner, resourceCreationDate);
        Environment environment = new Environment(LocalTime.parse(envTime), envLocation);

        return permissionAssignmentService.assignPermissions(role, resource, environment);
    }
}