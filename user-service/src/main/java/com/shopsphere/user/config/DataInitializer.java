package com.shopsphere.user.config;

import com.shopsphere.user.entity.User;
import com.shopsphere.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Configuration class for creating a demo admin account.
public class DataInitializer {

    @Bean
    CommandLineRunner createDemoAdmin(UserRepository repository, PasswordEncoder encoder) {
        return args -> {
            if (repository.findByEmail("admin@shopsphere.com").isEmpty()) {
                User admin = new User("ShopSphere Admin", "admin@shopsphere.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ADMIN");
                repository.save(admin);
            }
        };
    }
}
