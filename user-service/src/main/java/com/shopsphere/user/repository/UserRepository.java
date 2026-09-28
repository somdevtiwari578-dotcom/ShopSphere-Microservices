package com.shopsphere.user.repository;

import com.shopsphere.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data creates the query from the method name automatically.
    Optional<User> findByEmail(String email);
}
