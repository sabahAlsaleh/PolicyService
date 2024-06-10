package se.kth.PolicyService.model.AttributeConditions;

import se.kth.PolicyService.model.Attributes.Environment;

import java.time.LocalTime;

public class WorkingHoursCondition extends AttributeCondition<Environment> {
    @Override
    public boolean evaluate(Environment environment) {
        return environment.getTime().isAfter(LocalTime.of(8, 0)) && environment.getTime().isBefore(LocalTime.of(17, 0));
    }
}