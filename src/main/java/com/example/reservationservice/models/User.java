package com.example.reservationservice.models;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String nomETprenom;
    private String email;
    private String role;
    public Long getId() {
        return id;
    }

    public String getNomETprenom() {
        return nomETprenom;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNomETprenom(String nomETprenom) {
        this.nomETprenom = nomETprenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}