package se.kth.PolicyService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.PolicyService.model.DTO.User;
import se.kth.PolicyService.model.Policy;

import java.util.List;
import java.util.Map;

@Service
public class PolicyEvaluator {

    @Autowired
    private UserClient userClient;
    public String evaluateUserToRole(List<Policy> policies,Long userId, Map<String, String> environmentAttributes) {
        User user = userClient.getUserById(userId);

        for (Policy policy : policies) {
            if (policy.getType().equals("UserToRole") && evaluateCondition(policy.getCondition(), user, environmentAttributes)) {
                return policy.getAction().split("=")[1];
            }
        }
        return null;
    }

    public String evaluateUserToPermission(List<Policy> policies, Long userId, Map<String, String> resourceAttributes,
                                           Map<String, String> environmentAttributes) {
        User user = userClient.getUserById(userId);

        for (Policy policy : policies) {
            if (policy.getType().equals("UserToPermission") && evaluateCondition(policy.getCondition(), user, resourceAttributes,
                    environmentAttributes)) {
                return policy.getAction();
            }
        }
        return null;
    }

    private boolean evaluateCondition(String condition, User user, Map<String, String> environmentAttributes) {
        String[] conditions = condition.split(" AND ");
        for (String cond : conditions) {
            String[] keyValue = cond.split("=");
            if (keyValue.length != 2) {
                return false;
            }
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            switch (key) {
                case "user":
                    if (!user.getUsername().equals(value)) {
                        return false;
                    }
                    break;
                case "institution":
                    if (!user.getInstitution().equals(value)) {
                        return false;
                    }
                    break;
                case "position":
                    if (!user.getPosition().equals(value)) {
                        return false;
                    }
                    break;
                case "rank":
                    if (!user.getRank().equals(value)) {
                        return false;
                    }
                    break;
                case "environment":
                    if (!environmentAttributes.getOrDefault(key, "").equals(value)) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private boolean evaluateCondition(String condition, User user, Map<String, String> resourceAttributes, Map<String, String> environmentAttributes) {
        String[] conditions = condition.split(" AND ");
        for (String cond : conditions) {
            String[] keyValue = cond.split("=");
            if (keyValue.length != 2) {
                return false;
            }
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            switch (key) {
                case "user":
                    if (!user.getUsername().equals(value)) {
                        return false;
                    }
                    break;
                case "institution":
                    if (!user.getInstitution().equals(value)) {
                        return false;
                    }
                    break;
                case "position":
                    if (!user.getPosition().equals(value)) {
                        return false;
                    }
                    break;
                case "rank":
                    if (!user.getRank().equals(value)) {
                        return false;
                    }
                    break;
                case "resource_type":
                    if (!resourceAttributes.getOrDefault("resource_type", "").equals(value)) {
                        return false;
                    }
                    break;
                case "location":
                    if (!environmentAttributes.getOrDefault("location", "").equals(value)) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
   /* private boolean evaluateCondition(String condition, Map<String, String>... attributes) {

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

    */



}


// Implement condition evaluation logic based on attributes
// For simplicity, assuming conditions are in the format "key=value"