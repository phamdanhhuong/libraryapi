package com.library.libraryapi.services.impl;

import com.library.libraryapi.enums.NotificationType;
import com.library.libraryapi.models.*;
import com.library.libraryapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    private UsersRepository userRepository; // Để lấy danh sách tất cả người dùng

    @Autowired
    private NotificationSendLogRepository notificationSendLogRepository;

    public boolean wasNotificationSentToday(Long userId, String type) {
        LocalDate today = LocalDate.now();
        return notificationSendLogRepository.existsByUserIdAndTypeAndSendDate(userId, type, today);
    }

    public void saveNotificationSendLog(Long userId, String type) {
        NotificationSendLog log = new NotificationSendLog();
        log.setUserId(userId);
        log.setType(type);
        log.setSendDate(LocalDate.now());
        notificationSendLogRepository.save(log);
    }
    public void createNewBookNotificationForUser(Users user, Book newBook) {
        if (!wasNotificationSentToday(Long.valueOf(user.getUserId()), NotificationType.NEW_BOOK.name())) {
            Notification notification = new Notification();
            notification.setUserId(Long.valueOf(user.getUserId()));
            notification.setTitle("Sách mới!");
            notification.setMessage("Cuốn sách '" + newBook.getTitle() + "' vừa được phát hành.");
            notification.setType(NotificationType.NEW_BOOK.name());
            notification.setCreatedAt(LocalDateTime.now());
            notificationRepository.save(notification);
            saveNotificationSendLog(Long.valueOf(user.getUserId()), NotificationType.NEW_BOOK.name());
        }
    }

    public void createDueSoonNotificationForUser(BorrowingRecord record) {
        if (!wasNotificationSentToday(Long.valueOf(record.getUser().getUserId()), NotificationType.DUE_REMINDER.name())) {
            Notification notification = new Notification();
            notification.setUserId(Long.valueOf(record.getUser().getUserId()));
            notification.setTitle("Sắp đến hạn trả!");
            notification.setMessage("Sách '" + record.getBook().getTitle() + "' sắp đến hạn.");
            notification.setType(NotificationType.DUE_REMINDER.name());
            notification.setCreatedAt(LocalDateTime.now());
            notificationRepository.save(notification);
            saveNotificationSendLog(Long.valueOf(record.getUser().getUserId()), NotificationType.DUE_REMINDER.name());
        }
    }
    @Scheduled(cron = "0 0 8 * * ?") // Chạy mỗi ngày vào lúc 8:00 sáng
    public void checkNewBooksAndCreateNotifications() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth());
        List<Book> newBooks = bookRepository.findByPublicationDateBetween(startOfMonth, endOfMonth);

        if (!newBooks.isEmpty()) {
            List<Users> allUsers = userRepository.findAll(); // Lấy tất cả người dùng
            for (Users user : allUsers) {
                if (!wasNotificationSentToday(Long.valueOf(user.getUserId()), NotificationType.NEW_BOOK.name())) {
                    Notification notification = new Notification();
                    notification.setUserId(Long.valueOf(user.getUserId()));
                    notification.setTitle("Sách mới trong tháng!");
                    notification.setMessage("Có " + newBooks.size() + " sách mới đã được phát hành trong tháng này. Hãy khám phá ngay!");
                    notification.setType(NotificationType.NEW_BOOK.name());
                    notification.setCreatedAt(LocalDateTime.now());
                    notificationRepository.save(notification);
                    saveNotificationSendLog(Long.valueOf(user.getUserId()), NotificationType.NEW_BOOK.name());
                }
            }
        }
    }

    @Scheduled(cron = "0 0 9 * * ?") // Chạy mỗi ngày vào lúc 9:00 sáng
    public void checkDueBooksAndCreateNotifications() {
        LocalDate now = LocalDate.now();
        LocalDate threeDaysLater = now.plusDays(3);
        List<BorrowingRecord> overdueSoonRecords = borrowingRecordRepository.findAll().stream()
                .filter(record -> record.getReturnDate() == null &&
                        record.getDueDate() != null &&
                        !record.getDueDate().toLocalDate().isBefore(now) &&
                        record.getDueDate().toLocalDate().isBefore(threeDaysLater))
                .collect(Collectors.toList());

        for (BorrowingRecord record : overdueSoonRecords) {
            if (!wasNotificationSentToday(Long.valueOf(record.getUser().getUserId()), NotificationType.DUE_REMINDER.name())) {
                Notification notification = new Notification();
                notification.setUserId(Long.valueOf(record.getUser().getUserId()));
                notification.setTitle("Sách sắp đến hạn trả!");
                notification.setMessage("Sách '" + record.getBook().getTitle() + "' bạn đang mượn sẽ đến hạn trả vào ngày " + record.getDueDate().toLocalDate() + ".");
                notification.setType(NotificationType.DUE_REMINDER.name());
                notification.setCreatedAt(LocalDateTime.now());
                notificationRepository.save(notification);
                saveNotificationSendLog(Long.valueOf(record.getUser().getUserId()), NotificationType.DUE_REMINDER.name());
            }
        }
    }
}