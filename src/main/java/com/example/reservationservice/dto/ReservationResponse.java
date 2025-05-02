package com.example.reservationservice.dto;

import com.example.reservationservice.models.Salle;
import com.example.reservationservice.models.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationResponse {
    private Long id;
    private Salle salle;
    private User user;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;


    // Getters
    public Long getId() {
        return id;
    }

    public Salle getSalle() {
        return salle;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
