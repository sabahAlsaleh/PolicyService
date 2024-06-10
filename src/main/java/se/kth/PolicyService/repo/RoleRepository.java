package se.kth.PolicyService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.PolicyService.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
