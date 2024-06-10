package se.kth.PolicyService.model.AttributeConditions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.kth.PolicyService.model.Attributes.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCondition extends AttributeCondition<Role> {
    private String requiredRole;

    @Override
    public boolean evaluate(Role role) {
        return role.getName().equals(requiredRole);
    }
}
