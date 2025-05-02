package com.example.reservationservice.services;

import com.example.reservationservice.dto.ReservationRequest;
import com.example.reservationservice.dto.ReservationResponse;
import com.example.reservationservice.exceptions.ReservationConflictException;
import com.example.reservationservice.exceptions.ResourceNotFoundException;
import com.example.reservationservice.feignClient.SalleClient;
import com.example.reservationservice.feignClient.UserClient;
import com.example.reservationservice.models.*;
import com.example.reservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
//@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final SalleClient salleClient;
    private final UserClient userClient;
    public ReservationService(ReservationRepository reservationRepository,
                              SalleClient salleClient,
                              UserClient userClient) {
        this.reservationRepository = reservationRepository;
        this.salleClient = salleClient;
        this.userClient = userClient;
    }
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Transactional
    public ReservationResponse createReservation(ReservationRequest request) {
        // Vérifier si la salle existe
        Salle salle = salleClient.getSalleById(request.getSalleId());
        if (salle == null || !salle.getDisponible()) {
            throw new ResourceNotFoundException("Salle non disponible ou introuvable");
        }

        // Vérifier si l'utilisateur existe
        User user = userClient.getUserById(request.getUserId());
        if (user == null) {
            throw new ResourceNotFoundException("Utilisateur introuvable");
        }

        // Vérifier les conflits de réservation
        List<Reservation> conflictingReservations = reservationRepository.findBySalleIdAndStartTimeBetween(
                request.getSalleId(),
                request.getStartTime(),
                request.getEndTime()
        );

        if (!conflictingReservations.isEmpty()) {
            throw new ReservationConflictException("La salle est déjà réservée pour cette plage horaire");
        }

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setSalleId(request.getSalleId());
        reservation.setUserId(request.getUserId());
        reservation.setStartTime(request.getStartTime());
        reservation.setEndTime(request.getEndTime());
        reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);

        Reservation savedReservation = reservationRepository.save(reservation);

        return mapToReservationResponse(savedReservation, salle, user);
    }

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservation -> {
                    Salle salle = salleClient.getSalleById(reservation.getSalleId());
                    User user = userClient.getUserById(reservation.getUserId());
                    return mapToReservationResponse(reservation, salle, user);
                })
                .collect(Collectors.toList());
    }

    public ReservationResponse getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation introuvable"));

        Salle salle = salleClient.getSalleById(reservation.getSalleId());
        User user = userClient.getUserById(reservation.getUserId());

        return mapToReservationResponse(reservation, salle, user);
    }

    @Transactional
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation introuvable"));

        reservation.setStatus(Reservation.ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    public List<ReservationResponse> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId).stream()
                .map(reservation -> {
                    Salle salle = salleClient.getSalleById(reservation.getSalleId());
                    return mapToReservationResponse(reservation, salle, userClient.getUserById(userId));
                })
                .collect(Collectors.toList());
    }

    private ReservationResponse mapToReservationResponse(Reservation reservation, Salle salle, User user) {
        ReservationResponse response = new ReservationResponse();
        response.setId(reservation.getId());
        response.setSalle(salle);
        response.setUser(user);
        response.setStartTime(reservation.getStartTime());
        response.setEndTime(reservation.getEndTime());
        response.setStatus(reservation.getStatus().name());
        return response;
    }
}