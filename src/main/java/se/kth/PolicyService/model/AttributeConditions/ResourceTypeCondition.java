package se.kth.PolicyService.model.AttributeConditions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.kth.PolicyService.model.Attributes.Resource;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceTypeCondition extends AttributeCondition<Resource> {
    private String requiredType;


    @Override
    public boolean evaluate(Resource resource) {
        return resource.getType().equals(requiredType);
    }
}