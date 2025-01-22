package com.library.libraryapi.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.libraryapi.models.Reservation;
import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.Users;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.ReservationRepository;
import com.library.libraryapi.repository.UsersRepository;

@Service
public class ReservationService {
	@Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UsersRepository userRepository;

    public String reserveBooks(Integer userId, List<Integer> bookIds, LocalDateTime expirationDate) {
        Optional<Users> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return "User not found!";
        }

        List<String> messages = new ArrayList<>();

        for (Integer  bookId : bookIds) {
            Optional<Book> book = bookRepository.findById(bookId);

            if (book.isEmpty()) {
                messages.add("Book with ID " + bookId + " not found.");
                continue;
            }

            Book selectedBook = book.get();
            if (selectedBook.getAvailableQuantity() <= 0) {
                messages.add("Book with ID " + bookId + " is not available.");
                continue;
            }

            // Reduce available quantity
            selectedBook.setAvailableQuantity(selectedBook.getAvailableQuantity() - 1);
            bookRepository.save(selectedBook);

            // Create a new reservation
            Reservation reservation = new Reservation();
            reservation.setUser(user.get());
            reservation.setBook(selectedBook);
            reservation.setExpirationDate(expirationDate);

            reservationRepository.save(reservation);
            messages.add("Book with ID " + bookId + " reserved successfully.");
        }

        return String.join("\n", messages);
    }


    public List<Reservation> getUserReservations(Integer userId) {
        return reservationRepository.findByUserUserId(userId);
    }
}
