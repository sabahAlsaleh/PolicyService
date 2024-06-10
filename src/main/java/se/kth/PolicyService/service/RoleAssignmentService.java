package se.kth.PolicyService.service;

import org.springframework.stereotype.Service;
import se.kth.PolicyService.model.Attributes.Environment;
import se.kth.PolicyService.model.Attributes.User;
import se.kth.PolicyService.model.Policies.UserToRolePolicy;

import java.util.List;
@Service
public class RoleAssignmentService {

    private final List<UserToRolePolicy> userToRolePolicies;

    public RoleAssignmentService(List<UserToRolePolicy> userToRolePolicies) {
        this.userToRolePolicies = userToRolePolicies;
    }

    public String assignRole(User user, Environment environment) {
        for (UserToRolePolicy policy : userToRolePolicies) {
            String role = policy.evaluate(user, environment);
            if (role != null) {
                return role;
            }
        }
        return null;
    }
}
