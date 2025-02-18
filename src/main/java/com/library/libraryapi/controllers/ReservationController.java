package com.library.libraryapi.controllers;

import com.library.libraryapi.dto.ReservationRequest;
import com.library.libraryapi.models.Reservation;
import com.library.libraryapi.services.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final IReservationService reservationService;

    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveBooks(@RequestBody ReservationRequest reservationRequest) {
        try {
            String message = reservationService.reserveBooks(
                reservationRequest.getUserId(),
                reservationRequest.getBookIds(),
                reservationRequest.getExpirationDate()
            );
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserReservations(@PathVariable Integer userId) {
        List<Reservation> reservations = reservationService.getUserReservations(userId);
        if (reservations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reservations found for user ID " + userId);
        }
        return ResponseEntity.ok(reservations);
    }
}
