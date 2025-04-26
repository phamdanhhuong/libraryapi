package com.library.libraryapi.services;

import com.library.libraryapi.models.BorrowingRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IBorrowingRecordService {
    String confirmMultipleBorrowings(Integer reservationId);
    List<BorrowingRecord> getBorrowingRecordsByUser(Integer userId);
    Optional<BorrowingRecord> getBorrowingRecordById(Integer recordId);
    String renewBorrowingRecord(BorrowingRecord borrowingRecord, LocalDateTime newDueDate);
}
