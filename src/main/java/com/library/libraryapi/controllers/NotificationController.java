package com.library.libraryapi.controllers;

import com.library.libraryapi.dto.CreateNotificationRequest;
import com.library.libraryapi.models.Notification;
import com.library.libraryapi.repository.NotificationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping
    public ResponseEntity<Notification> createNotification(@Valid @RequestBody CreateNotificationRequest request) { // Thay đổi kiểu trả về ở đây
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);

        Notification savedNotification = notificationRepository.save(notification); // Lưu và lấy đối tượng đã lưu
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotification); // Trả về 201 Created với đối tượng Notification
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable Long userId) {
        List<Notification> notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(userId); // Sắp xếp theo createdAt giảm dần
        return ResponseEntity.ok(notifications);
    }
}