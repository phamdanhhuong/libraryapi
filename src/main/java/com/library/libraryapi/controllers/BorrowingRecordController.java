package com.library.libraryapi.controllers;

import com.library.libraryapi.services.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/borrowings")
public class BorrowingRecordController {
    
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/confirm")
    public String confirmMultipleBorrowings(@RequestBody Map<String, Object> requestData) {
        Integer reservationId = (Integer) requestData.get("reservationId");
        LocalDateTime dueDate = LocalDateTime.parse((String) requestData.get("dueDate"));
        return borrowingRecordService.confirmMultipleBorrowings(reservationId, dueDate);
    }
}
