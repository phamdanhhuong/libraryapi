package com.library.libraryapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId; // Sử dụng Long thay vì String nếu khóa ngoại là số

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500) // Đặt độ dài phù hợp
    private String message;

    @Column(nullable = false)
    private String type;

    @CreationTimestamp // Tự động tạo timestamp khi tạo entity
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    // Các trường khác nếu cần
}