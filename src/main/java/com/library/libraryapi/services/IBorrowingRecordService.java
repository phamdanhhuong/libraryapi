package com.library.libraryapi.services;

import com.library.libraryapi.models.BorrowingRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface IBorrowingRecordService {
    String confirmMultipleBorrowings(Integer reservationId);
    List<BorrowingRecord> getBorrowingRecordsByUser(Integer userId);
}
