package com.library.libraryapi.models;

import java.math.BigDecimal;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "renewalCount", nullable = false, columnDefinition = "integer default 0")
    private Integer renewalCount = 0;

    @Column(name = "penaltyFee", columnDefinition = "decimal(10, 2) default 0.00")
    private BigDecimal penaltyFee = BigDecimal.ZERO;

    // Kiểm tra returnDate hợp lệ
    public void setReturnDate(LocalDateTime returnDate) {
        if (returnDate != null && returnDate.isBefore(this.borrowDate)) {
            throw new IllegalArgumentException("Return date must be after borrow date.");
        }
        this.returnDate = returnDate;
        this.status = "RETURNED";
    }

    public void incrementRenewalCount() {
        this.renewalCount++;
    }
}