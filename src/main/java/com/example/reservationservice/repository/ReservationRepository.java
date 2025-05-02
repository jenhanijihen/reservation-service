package com.example.reservationservice.repository;

import com.example.reservationservice.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findBySalleIdAndStartTimeBetween(Long salleId, LocalDateTime start, LocalDateTime end);
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByStatus(Reservation.ReservationStatus status);
}