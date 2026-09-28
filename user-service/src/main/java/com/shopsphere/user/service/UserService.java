package com.shopsphere.user.service;

import com.shopsphere.user.dto.LoginRequest;
import com.shopsphere.user.dto.LoginResponse;
import com.shopsphere.user.entity.User;
import com.shopsphere.user.repository.UserRepository;
import com.shopsphere.user.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Business/service layer managed by Spring.
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User register(User user) {
        // Password is hashed before it is saved; plain passwords are never stored.
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Public registration always creates a normal USER account.
        // ADMIN accounts are created separately for the demo.
        user.setRole("USER");
        return repository.save(user); // JpaRepository save() inserts the entity.
    }

    public LoginResponse login(LoginRequest request) {
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // matches() compares the entered password with the BCrypt hash in MySQL.
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new LoginResponse(token, user.getRole());
    }

    public List<User> all() { return repository.findAll(); }

    public User byId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
