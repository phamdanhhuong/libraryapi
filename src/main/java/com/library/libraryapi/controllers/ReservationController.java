package com.library.libraryapi.controllers;

import com.library.libraryapi.dto.ApiResponse;
import com.library.libraryapi.dto.ReservationRequest;
import com.library.libraryapi.models.Book;
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
            ApiResponse response = ApiResponse.builder()
    				.message(message)
    				.status(true)
    				.data(null)
    				.build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            ApiResponse response = ApiResponse.builder()
    				.message(e.getMessage())
    				.status(false)
    				.data(null)
    				.build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserReservations(@PathVariable Integer userId) {
        List<Reservation> reservations = reservationService.getReservationsByUser(userId);
        if (reservations.isEmpty()) {
            ApiResponse response = ApiResponse.builder()
    				.message("No reservations found for user ID " + userId)
    				.status(false)
    				.data(null)
    				.build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse response = ApiResponse.builder()
				.message("Reservations fetched successfully")
				.status(true)
				.data(reservations)
				.build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{reservationId}/books")
    public ResponseEntity<ApiResponse> getBooksByReservationId(@PathVariable Integer reservationId) {
        try {
            List<Book> books = reservationService.getBooksByReservationId(reservationId);
            ApiResponse response = ApiResponse.builder()
                    .message("Books fetched successfully")
                    .status(true)
                    .data(books)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            ApiResponse response = ApiResponse.builder()
                    .message(e.getMessage())
                    .status(false)
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
