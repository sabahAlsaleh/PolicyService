package se.kth.PolicyService.model.AttributeConditions;

import se.kth.PolicyService.model.Attributes.User;

public class PositionCondition extends AttributeCondition<User> {
    private String requiredPosition;

    public PositionCondition(String requiredPosition) {
        this.requiredPosition = requiredPosition;
    }

    @Override
    public boolean evaluate(User user) {
        return user.getPosition().equals(requiredPosition);
    }
}