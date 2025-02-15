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
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationBookRepository reservationBookRepository;

    @Autowired
    private BookRepository bookRepository;

    public String confirmMultipleBorrowings(Integer reservationId, LocalDateTime dueDate) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isEmpty()) {
            return "Reservation not found.";
        }

        Reservation reservation = optionalReservation.get();
        if (reservation.getStatus() != Reservation.ReservationStatus.PENDING) {
            return "Reservation is not approved.";
        }

        List<ReservationBook> reservationBooks = reservationBookRepository.findByReservation(reservation);
        List<String> messages = new ArrayList<>();

        for (ReservationBook rb : reservationBooks) {
            Book book = rb.getBook();
            if (book.getAvailableQuantity() <= 0) {
                messages.add("Book with ID " + book.getBookId() + " is not available.");
                continue;
            }

            // Tạo Borrowing Record
            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setUser(reservation.getUser());
            borrowingRecord.setBook(book);
            borrowingRecord.setBorrowDate(LocalDateTime.now());
            borrowingRecord.setDueDate(dueDate);
            borrowingRecord.setStatus("BORROWED");

            borrowingRecordRepository.save(borrowingRecord);

            // Cập nhật số lượng sách
            book.setAvailableQuantity(book.getAvailableQuantity() - 1);
            book.setBorrowedCount(book.getBorrowedCount() + 1);
            bookRepository.save(book);
        }

        // Cập nhật trạng thái reservation thành COMPLETED
        reservation.setStatus(Reservation.ReservationStatus.COMPLETED);
        reservationRepository.save(reservation);

        return messages.isEmpty() ? "All books borrowed successfully." : String.join("\n", messages);
    }
}
