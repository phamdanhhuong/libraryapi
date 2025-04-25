package com.library.libraryapi.controllers;

import com.library.libraryapi.models.BorrowingRecord;
import com.library.libraryapi.services.IBorrowingRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/borrowings")
public class BorrowingRecordController {

    private final IBorrowingRecordService borrowingRecordService;

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
}
