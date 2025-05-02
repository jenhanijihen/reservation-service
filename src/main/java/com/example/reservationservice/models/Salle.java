package com.example.reservationservice.models;

import lombok.Data;

@Data
public class Salle {
    private Long id;
    private String nom;
    private Integer capacite;
    private String type;
    private Boolean disponible;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public String getType() {
        return type;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}