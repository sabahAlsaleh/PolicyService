package se.kth.PolicyService.model;

import lombok.Data;

import java.util.Map;

@Data
public class EvaluatePermissionRequest {

    private Map<String, String> userAttributes;
    private Map<String, String> resourceAttributes;
    private Map<String, String> environmentAttributes;

}
