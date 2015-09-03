package ua.com.homebudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.homebudget.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
