package se.kth.PolicyService.service;

import org.springframework.stereotype.Service;
import se.kth.PolicyService.model.Attributes.Environment;
import se.kth.PolicyService.model.Attributes.Resource;
import se.kth.PolicyService.model.Attributes.Role;
import se.kth.PolicyService.model.Policies.UserToPermissionPolicy;

import java.util.List;
@Service
public class PermissionAssignmentService {
    private final List<UserToPermissionPolicy> userToPermissionPolicies;

    public PermissionAssignmentService(List<UserToPermissionPolicy> userToPermissionPolicies) {
        this.userToPermissionPolicies = userToPermissionPolicies;
    }

    public List<String> assignPermissions(Role role, Resource resource, Environment environment) {
        for (UserToPermissionPolicy policy : userToPermissionPolicies) {
            List<String> permissions = policy.evaluate(role, resource, environment);
            if (!permissions.isEmpty()) {
                return permissions;
            }
        }
        return List.of();
    }
}
