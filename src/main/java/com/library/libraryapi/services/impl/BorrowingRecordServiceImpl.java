package com.library.libraryapi.services.impl;

import com.library.libraryapi.models.*;
import com.library.libraryapi.repository.*;
import com.library.libraryapi.services.IBorrowingRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordServiceImpl implements IBorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationBookRepository reservationBookRepository;
    private final BookRepository bookRepository;

    public BorrowingRecordServiceImpl(
            BorrowingRecordRepository borrowingRecordRepository,
            ReservationRepository reservationRepository,
            ReservationBookRepository reservationBookRepository,
            BookRepository bookRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.reservationRepository = reservationRepository;
        this.reservationBookRepository = reservationBookRepository;
        this.bookRepository = bookRepository;
    }

    @Override
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
            BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                    .user(reservation.getUser())
                    .book(book)
                    .borrowDate(LocalDateTime.now())
                    .dueDate(dueDate)
                    .status("BORROWED")
                    .build();

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
