package com.library.libraryapi.controllers;

import com.library.libraryapi.services.IBorrowingRecordService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
}
