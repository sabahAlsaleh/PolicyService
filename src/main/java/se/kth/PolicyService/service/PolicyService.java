package se.kth.PolicyService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.PolicyService.model.Policy;
import se.kth.PolicyService.repo.PolicyRepository;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id).orElse(null);
    }

    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    public Policy updatePolicy(Long id, Policy policyDetails) {
        Policy policy = policyRepository.findById(id).orElse(null);
        if (policy != null) {
            policy.setType(policyDetails.getType());
            policy.setCondition(policyDetails.getCondition());
            policy.setAction(policyDetails.getAction());
            return policyRepository.save(policy);
        }
        return null;
    }

    public void deletePolicy(Long id) {
        policyRepository.deleteById(id);
    }
}