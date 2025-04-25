package com.library.libraryapi.models;

import com.library.libraryapi.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "notification_send_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSendLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String type; // Ví dụ: NEW_BOOK, DUE_REMINDER

    @Column(name = "send_date", nullable = false)
    private LocalDate sendDate;

    @Enumerated(EnumType.STRING) // Nếu NotificationType là enum ở backend
    @Column(name = "notification_type")
    private NotificationType notificationTypeEnum;

    public NotificationSendLog(Long userId, NotificationType notificationTypeEnum, LocalDate sendDate) {
        this.userId = userId;
        this.notificationTypeEnum = notificationTypeEnum;
        this.sendDate = sendDate;
    }
}