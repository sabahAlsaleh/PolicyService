package se.kth.PolicyService.model.AttributeConditions;

import se.kth.PolicyService.model.Attributes.Environment;

public class LocationCondition extends AttributeCondition<Environment> {
    private String requiredLocation;

    public LocationCondition(String requiredLocation) {
        this.requiredLocation = requiredLocation;
    }

    @Override
    public boolean evaluate(Environment environment) {
        return environment.getLocation().equals(requiredLocation);
    }
}