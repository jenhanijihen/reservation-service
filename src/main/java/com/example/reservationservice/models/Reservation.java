package com.example.reservationservice.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long salleId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public enum ReservationStatus {
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }
    public Long getId() {
        return id;
    }

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

    public ReservationStatus getStatus() {
        return status;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

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

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
