package com.library.libraryapi.services.impl;

import com.library.libraryapi.models.*;
import com.library.libraryapi.repository.*;
import com.library.libraryapi.services.IBorrowingRecordService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private final UsersRepository usersRepository;

    public BorrowingRecordServiceImpl(
            BorrowingRecordRepository borrowingRecordRepository,
            ReservationRepository reservationRepository,
            ReservationBookRepository reservationBookRepository,
            BookRepository bookRepository,
            UsersRepository usersRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.reservationRepository = reservationRepository;
        this.reservationBookRepository = reservationBookRepository;
        this.bookRepository = bookRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public String confirmMultipleBorrowings(Integer reservationId) {
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

            // Tính toán dueDate dựa trên logic của bạn (ví dụ: 7 ngày kể từ ngày mượn)
            LocalDateTime calculatedDueDate = LocalDateTime.now().plusDays(7); // Ví dụ: 7 ngày
            // Tạo Borrowing Record
            BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                    .user(reservation.getUser())
                    .book(book)
                    .borrowDate(LocalDateTime.now())
                    .dueDate(calculatedDueDate)
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
    @Override
    public List<BorrowingRecord> getBorrowingRecordsByUser(Integer userId) {
        Optional<Users> userOptional = usersRepository.findById(userId); // Use UsersRepository
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found"); // Handle the case where the user doesn't exist
        }
        Users user = userOptional.get();
        return borrowingRecordRepository.findByUser(user);
    }
    @Override
    public Optional<BorrowingRecord> getBorrowingRecordById(Integer recordId) {
        return borrowingRecordRepository.findById(recordId);
    }

    @Override
    public String renewBorrowingRecord(BorrowingRecord borrowingRecord, LocalDateTime newDueDate) {
        LocalDateTime currentDueDate = borrowingRecord.getDueDate();
        LocalDate currentDueDateLocal = currentDueDate.toLocalDate();
        LocalDate now = LocalDate.now();

        LocalDateTime finalDueDate = newDueDate;
        // Kiểm tra nếu gia hạn sau ngày hết hạn thì áp dụng phí phạt
        if (now.isAfter(currentDueDateLocal)) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(currentDueDateLocal, now);
            double penaltyFee = daysLate * 0.5;
            borrowingRecord.setPenaltyFee(BigDecimal.valueOf(penaltyFee));
            return "ERROR: Book is overdue. A penalty fee of " + penaltyFee + " will be applied.";
        }

        borrowingRecord.setDueDate(finalDueDate);
        borrowingRecord.incrementRenewalCount();
        try {
            borrowingRecordRepository.save(borrowingRecord);
            return "SUCCESS";
        } catch (Exception e) {
            return "ERROR: Failed to renew the book due to a database error.";
        }
    }
}
