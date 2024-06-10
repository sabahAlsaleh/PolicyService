package se.kth.PolicyService.model.Policies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.kth.PolicyService.model.Attributes.Role;
import se.kth.PolicyService.model.AttributeConditions.AttributeCondition;
import se.kth.PolicyService.model.Attributes.Environment;
import se.kth.PolicyService.model.Attributes.Resource;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToPermissionPolicy {
    private String name;
    private AttributeCondition<Role> roleCondition;
    private AttributeCondition<Resource> resourceCondition;
    private AttributeCondition<Environment> environmentCondition;
    private List<String> permissions;


    public List<String> evaluate(Role role, Resource resource, Environment environment) {
        if (roleCondition.evaluate(role) && resourceCondition.evaluate(resource)
                && environmentCondition.evaluate(environment)) {
            return permissions;
        }
        return List.of();
    }
}