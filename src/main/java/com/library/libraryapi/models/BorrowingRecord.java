package com.library.libraryapi.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrowing_records")
public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @Column(name = "borrowDate", nullable = false)
    private LocalDateTime borrowDate = LocalDateTime.now();

    @Column(name = "dueDate", nullable = false)
    private LocalDateTime dueDate; // Ngày phải trả sách

    @Column(name = "returnDate")
    private LocalDateTime returnDate; // Ngày thực tế trả sách

    @Column(name = "status", nullable = false, length = 20)
    private String status = "BORROWED"; // "BORROWED", "RETURNED", "OVERDUE"

    // Kiểm tra returnDate hợp lệ
    public void setReturnDate(LocalDateTime returnDate) {
        if (returnDate != null && returnDate.isBefore(this.borrowDate)) {
            throw new IllegalArgumentException("Return date must be after borrow date.");
        }
        this.returnDate = returnDate;
        this.status = "RETURNED";
    }
}
