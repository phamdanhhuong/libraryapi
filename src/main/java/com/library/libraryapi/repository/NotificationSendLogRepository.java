package com.library.libraryapi.repository;

import com.library.libraryapi.enums.NotificationType;
import com.library.libraryapi.models.NotificationSendLog; // Nếu NotificationType là enum ở backend
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificationSendLogRepository extends JpaRepository<NotificationSendLog, Long> {
    boolean existsByUserIdAndTypeAndSendDate(Long userId, String type, LocalDate sendDate);

    // Nếu bạn dùng enum NotificationType ở backend:
    boolean existsByUserIdAndNotificationTypeEnumAndSendDate(Long userId, NotificationType notificationTypeEnum, LocalDate sendDate);

    List<NotificationSendLog> findByUserIdAndTypeOrderBySendDateDesc(Long userId, String type);

    // Nếu bạn dùng enum NotificationType ở backend:
    List<NotificationSendLog> findByUserIdAndNotificationTypeEnumOrderBySendDateDesc(Long userId, NotificationType notificationTypeEnum);
}