package se.kth.PolicyService.model.AttributeConditions;

public abstract class AttributeCondition<T> {
    public abstract boolean evaluate(T t);
}