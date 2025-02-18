package com.library.libraryapi.services;

import com.library.libraryapi.models.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface IReservationService {
    String reserveBooks(Integer userId, List<Integer> bookIds, LocalDateTime expirationDate);
    List<Reservation> getUserReservations(Integer userId);
}
