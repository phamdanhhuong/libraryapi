package com.library.libraryapi.dto;

import com.library.libraryapi.models.BorrowingRecord;
import lombok.Data;

import java.util.List;

@Data
public class DueSoonBookResponse {
    private List<BorrowingRecord> dueSoonBooks;
}