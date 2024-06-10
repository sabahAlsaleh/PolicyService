package se.kth.PolicyService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.PolicyService.model.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}