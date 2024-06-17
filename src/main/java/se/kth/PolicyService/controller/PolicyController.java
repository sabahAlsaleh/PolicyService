package se.kth.PolicyService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.kth.PolicyService.model.EvaluatePermissionRequest;
import se.kth.PolicyService.model.Policy;
import se.kth.PolicyService.service.PolicyEvaluator;
import se.kth.PolicyService.service.PolicyService;

import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/api/policies")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @Autowired
    private PolicyEvaluator policyEvaluator;

    @GetMapping("getAll")
    @PreAuthorize("hasRole('DataOwner')")
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @GetMapping("getPolicyById/{id}")
    @PreAuthorize("hasRole('DataOwner')")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long id) {
        Policy policy = policyService.getPolicyById(id);
        if (policy != null) {
            return ResponseEntity.ok(policy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('DataOwner')")
    public Policy createPolicy(@RequestBody Policy policy) {
        return policyService.createPolicy(policy);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('DataOwner')")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Long id, @RequestBody Policy policyDetails) {
        Policy updatedPolicy = policyService.updatePolicy(id, policyDetails);
        if (updatedPolicy != null) {
            return ResponseEntity.ok(updatedPolicy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('DataOwner')")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/evaluate-role")
    @PreAuthorize("hasRole('DataOwner')")
    public ResponseEntity<String> evaluateUserToRole(@RequestParam Long userId,
                                                     @RequestBody Map<String, String> environmentAttributes) {
        List<Policy> policies = policyService.getAllPolicies();
        String role = policyEvaluator.evaluateUserToRole(policies, userId, environmentAttributes);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.badRequest().body("No matching role found");
        }
    }

    @PostMapping("/evaluate-permission")
    @PreAuthorize("hasRole('DataOwner')")
    public ResponseEntity<String> evaluateUserToPermission(@RequestParam Long userId,
                                                           @RequestBody EvaluatePermissionRequest request) {
        List<Policy> policies = policyService.getAllPolicies();
        String permission = policyEvaluator.evaluateUserToPermission(
                policies,
                userId,
                request.getResourceAttributes(),
                request.getEnvironmentAttributes()
        );
        if (permission != null) {
            return ResponseEntity.ok(permission);
        } else {
            return ResponseEntity.badRequest().body("No matching permission found");
        }

    }



}
