package com.library.libraryapi.services;

import java.time.LocalDateTime;

public interface IBorrowingRecordService {
    String confirmMultipleBorrowings(Integer reservationId, LocalDateTime dueDate);
}
