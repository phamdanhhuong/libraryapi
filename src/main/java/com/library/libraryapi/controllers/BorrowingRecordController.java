package com.library.libraryapi.controllers;

import com.library.libraryapi.dto.ApiResponse;
import com.library.libraryapi.dto.ApiResponseWithNoData;
import com.library.libraryapi.dto.DueSoonBookResponse;
import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.BorrowingRecord;
import com.library.libraryapi.models.Users;
import com.library.libraryapi.repository.BorrowingRecordRepository;
import com.library.libraryapi.repository.UsersRepository;
import com.library.libraryapi.services.IBorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/borrowings")
public class BorrowingRecordController {

    private final IBorrowingRecordService borrowingRecordService;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;
    public BorrowingRecordController(IBorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @PostMapping("/confirm")
    public String confirmMultipleBorrowings(@RequestBody Map<String, Object> requestData) {
        Integer reservationId = (Integer) requestData.get("reservationId");
        return borrowingRecordService.confirmMultipleBorrowings(reservationId);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BorrowingRecord>> getBorrowingRecordsByUser(@PathVariable Integer userId) {
        try {
            List<BorrowingRecord> records = borrowingRecordService.getBorrowingRecordsByUser(userId);
            return ResponseEntity.ok(records);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }
    }
    @GetMapping("/user/{userId}/due-soon")
    public ResponseEntity<List<BorrowingRecord>> getDueSoonBooksByUser(@RequestParam Integer userId,
                                                              @RequestParam(value = "days", required = false, defaultValue = "3") int days) {
        LocalDate now = LocalDate.now();
        LocalDate dueDateThreshold = now.plusDays(days);

        Optional<Users> userOptional = usersRepository.findById(userId); // Use UsersRepository
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found"); // Handle the case where the user doesn't exist
        }
        Users user = userOptional.get();

        List<BorrowingRecord> dueSoonBooks = borrowingRecordRepository.findByUserAndReturnDateIsNull(user).stream()
                .filter(record -> record.getDueDate() != null &&
                        !record.getDueDate().toLocalDate().isBefore(now) &&
                        record.getDueDate().toLocalDate().isBefore(dueDateThreshold))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dueSoonBooks);
    }
    @PostMapping("/{recordId}/renew")
    public ResponseEntity<ApiResponseWithNoData> renewBorrowingRecord(
            @PathVariable Integer recordId,
            @RequestParam LocalDate renewalDate) {

        Optional<BorrowingRecord> borrowingRecordOptional = borrowingRecordService.getBorrowingRecordById(recordId);

        if (borrowingRecordOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseWithNoData(false, "Borrowing record not found."));
        }

        BorrowingRecord borrowingRecord = borrowingRecordOptional.get();

        if (!borrowingRecord.getStatus().equalsIgnoreCase("BORROWED")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseWithNoData(false, "This book cannot be renewed as it is not currently borrowed."));
        }

        LocalDateTime currentDueDate = borrowingRecord.getDueDate();
        LocalDate currentDueDateLocal = currentDueDate.toLocalDate();
        LocalDate now = LocalDate.now();

        if (renewalDate.isBefore(currentDueDateLocal)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseWithNoData(false, "Renewal date cannot be before the current due date."));
        }

        if (renewalDate.isAfter(currentDueDateLocal.plusDays(7))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseWithNoData(false, "Renewal date cannot be more than 7 days after the current due date."));
        }

        // Gọi service để thực hiện gia hạn
        String renewalResult = borrowingRecordService.renewBorrowingRecord(borrowingRecord, renewalDate.atStartOfDay());

        if (renewalResult.equals("SUCCESS")) {
            return ResponseEntity.ok(new ApiResponseWithNoData(true, "Book renewed successfully until " + renewalDate + "."));
        } else if (renewalResult.startsWith("ERROR:")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseWithNoData(false, renewalResult.substring(6)));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseWithNoData(false, "An unexpected error occurred during renewal."));
        }
    }
}
