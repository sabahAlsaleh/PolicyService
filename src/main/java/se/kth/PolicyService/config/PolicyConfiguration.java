package se.kth.PolicyService.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.kth.PolicyService.model.AttributeConditions.*;
import se.kth.PolicyService.model.Policies.UserToPermissionPolicy;
import se.kth.PolicyService.model.Policies.UserToRolePolicy;
import se.kth.PolicyService.service.PermissionAssignmentService;
import se.kth.PolicyService.service.RoleAssignmentService;

import java.util.List;

@Configuration
public class PolicyConfiguration {

    @Bean
    public RoleAssignmentService roleAssignmentService() {
        List<UserToRolePolicy> userToRolePolicies = List.of(
                new UserToRolePolicy(
                        "DoctorRolePolicy",
                        new PositionCondition("doctor"),
                        new WorkingHoursCondition(),
                        "CardiologyDoctor"
                )
        );
        return new RoleAssignmentService(userToRolePolicies);
    }

    @Bean
    public PermissionAssignmentService permissionAssignmentService() {
        List<UserToPermissionPolicy> userToPermissionPolicies = List.of(
                new UserToPermissionPolicy(
                        "CardiologyDoctorPermissionPolicy",
                        new RoleCondition("CardiologyDoctor"),
                        new ResourceTypeCondition("patient_record"),
                        new LocationCondition("hospital"),
                        List.of("read", "write")
                )
        );
        return new PermissionAssignmentService(userToPermissionPolicies);
    }
}