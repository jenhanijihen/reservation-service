package com.example.reservationservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {
    private Long salleId;
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Getters
    public Long getSalleId() {
        return salleId;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    // Setters
    public void setSalleId(Long salleId) {
        this.salleId = salleId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
