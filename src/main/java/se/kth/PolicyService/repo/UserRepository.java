package se.kth.PolicyService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.PolicyService.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}