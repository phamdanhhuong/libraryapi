package com.library.libraryapi.services;

import com.library.libraryapi.models.*;
import com.library.libraryapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationBookRepository reservationBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UsersRepository userRepository;

    public String reserveBooks(Integer userId, List<Integer> bookIds, LocalDateTime expirationDate) {
        Optional<Users> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return "User not found!";
        }

        // Tạo một Reservation mới
        Reservation reservation = new Reservation();
        reservation.setUser(user.get());
        reservation.setExpirationDate(expirationDate);
        reservation.setStatus(Reservation.ReservationStatus.PENDING);
        reservationRepository.save(reservation);

        List<String> messages = new ArrayList<>();
        for (Integer bookId : bookIds) {
            Optional<Book> book = bookRepository.findById(bookId);
            if (book.isEmpty()) {
                messages.add("Book with ID " + bookId + " not found.");
                continue;
            }

            // Lưu vào bảng trung gian ReservationBook
            ReservationBook reservationBook = new ReservationBook();
            reservationBook.setReservation(reservation);
            reservationBook.setBook(book.get());
            reservationBookRepository.save(reservationBook);

            messages.add("Book with ID " + bookId + " reserved successfully.");
        }

        return String.join("\n", messages);
    }

    public List<Reservation> getUserReservations(Integer userId) {
        return reservationRepository.findByUserUserId(userId);
    }
}
