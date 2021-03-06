package ua.com.homebudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.homebudget.model.Role;

/**
 * Repository for role
 *
 * @author Bondar Dmytro.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
