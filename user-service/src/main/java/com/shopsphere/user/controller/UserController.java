package com.shopsphere.user.controller;

import com.shopsphere.user.dto.LoginRequest;
import com.shopsphere.user.dto.LoginResponse;
import com.shopsphere.user.entity.User;
import com.shopsphere.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Converts this class into a REST controller.
@RequestMapping("/users") // Base URL for this service's APIs.
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody User user) {
        // Registration is public so a new user can create an account.
        return service.register(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        // Login checks the password and returns a signed JWT token.
        return service.login(request);
    }

    @GetMapping
    public List<User> all() { return service.all(); }

    @GetMapping("/{id}")
    public User byId(@PathVariable Long id) {
        // @PathVariable reads id from the URL.
        return service.byId(id);
    }
}
