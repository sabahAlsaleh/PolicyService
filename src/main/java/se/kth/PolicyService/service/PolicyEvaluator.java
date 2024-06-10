package se.kth.PolicyService.service;


import org.springframework.stereotype.Service;
import se.kth.PolicyService.model.Policy;

import java.util.List;
import java.util.Map;

@Service
public class PolicyEvaluator {

    public String evaluateUserToRole(List<Policy> policies, Map<String, String> userAttributes, Map<String, String> environmentAttributes) {
        for (Policy policy : policies) {
            if (policy.getType().equals("UserToRole") && evaluateCondition(policy.getCondition(), userAttributes, environmentAttributes)) {
                return policy.getAction().split("=")[1];
            }
        }
        return null;
    }

    public String evaluateUserToPermission(List<Policy> policies, Map<String, String> userAttributes, Map<String, String> resourceAttributes, Map<String, String> environmentAttributes) {
        for (Policy policy : policies) {
            if (policy.getType().equals("UserToPermission") && evaluateCondition(policy.getCondition(), userAttributes, resourceAttributes, environmentAttributes)) {
                return policy.getAction();
            }
        }
        return null;
    }

    private boolean evaluateCondition(String condition, Map<String, String>... attributes) {
        // Implement condition evaluation logic based on attributes
        // For simplicity, assuming conditions are in the format "key=value"
        String[] parts = condition.split("AND");
        for (String part : parts) {
            String[] keyValue = part.trim().split("=");
            boolean match = false;
            for (Map<String, String> attr : attributes) {
                if (attr.getOrDefault(keyValue[0].trim(), "").equals(keyValue[1].trim())) {
                    match = true;
                    break;
                }
            }
            if (!match) return false;
        }
        return true;
    }
}