package com.shopsphere.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity // JPA maps this Java class to a database table.
public class User {

    @Id // Primary key.
    @GeneratedValue(strategy=GenerationType.IDENTITY) // Database generates id automatically.
    private Long id;

    @NotBlank // Validation library: value cannot be blank.
    private String name;

    @NotBlank
    @Email // Validation library: checks basic email format.
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Password can be sent in JSON but is never returned in API responses.
    @NotBlank
    private String password;

    private String role = "USER"; // Simple role used by Spring Security.

    public User() { /* JPA requires a no-argument constructor. */ }
    public User(String name,String email) {
        this.name=name; this.email=email;
    }

    public Long getId(){ return id; }
    public String getName(){ return name; }
    public void setName(String name){ this.name=name; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email=email; }
    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password=password; }
    public String getRole(){ return role; }
    public void setRole(String role){ this.role=role; }

}
