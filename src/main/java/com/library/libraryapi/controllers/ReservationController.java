package com.library.libraryapi.controllers;

import com.library.libraryapi.dto.ApiResponse;
import com.library.libraryapi.dto.ReservationRequest;
import com.library.libraryapi.models.Reservation;
import com.library.libraryapi.models.ReservationBook;
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
    public ResponseEntity<ApiResponse> reserveBooks(@RequestBody ReservationRequest reservationRequest) {
        try {
            String message = reservationService.reserveBooks(
                reservationRequest.getUserId(),
                reservationRequest.getBookIds(),
                reservationRequest.getExpirationDate()
            );
            ApiResponse response = new ApiResponse(true, message, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            ApiResponse response = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserReservations(@PathVariable Integer userId) {
        List<Reservation> reservations = reservationService.getReservationsByUser(userId);
        if (reservations.isEmpty()) {
            ApiResponse response = new ApiResponse(false, "No reservations found for user ID " + userId, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse response = new ApiResponse(true, "Reservations fetched successfully", reservations);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
