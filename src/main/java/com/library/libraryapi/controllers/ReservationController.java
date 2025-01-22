package com.library.libraryapi.controllers;

import com.library.libraryapi.dto.ReservationRequest;
import com.library.libraryapi.models.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryapi.services.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	@Autowired
    private ReservationService reservationService;
	
	@PostMapping("/reserve")
	public ResponseEntity<String> reserveBooks(@RequestBody ReservationRequest reservationRequest) {
	    String message = reservationService.reserveBooks(
	        reservationRequest.getUserId(),
	        reservationRequest.getBookIds(),
	        reservationRequest.getExpirationDate()
	    );
	    return ResponseEntity.ok(message);
	}

	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getUserReservations(@PathVariable Integer userId) {
        List<Reservation> reservations = reservationService.getUserReservations(userId);
        return ResponseEntity.ok(reservations);
    }
}
