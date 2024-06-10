package se.kth.PolicyService.model.Policies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.kth.PolicyService.model.Attributes.User;
import se.kth.PolicyService.model.AttributeConditions.AttributeCondition;
import se.kth.PolicyService.model.Attributes.Environment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToRolePolicy {
    private String name;
    private AttributeCondition<User> userCondition;
    private AttributeCondition<Environment> environmentCondition;
    private String roleName;


    public String evaluate(User user, Environment environment) {
        if (userCondition.evaluate(user) && environmentCondition.evaluate(environment)) {
            return roleName;
        }
        return null;
    }
}