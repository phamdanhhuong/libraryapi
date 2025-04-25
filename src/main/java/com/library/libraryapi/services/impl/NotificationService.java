package com.library.libraryapi.services.impl;

import com.library.libraryapi.enums.NotificationType;
import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.BorrowingRecord;
import com.library.libraryapi.models.Notification;
import com.library.libraryapi.models.Users;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.BorrowingRecordRepository;
import com.library.libraryapi.repository.NotificationRepository;
import com.library.libraryapi.repository.UsersRepository;
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

    @Scheduled(cron = "0 0 8 * * ?") // Chạy mỗi ngày vào lúc 8:00 sáng
    public void checkNewBooksAndCreateNotifications() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth());
        List<Book> newBooks = bookRepository.findByPublicationDateBetween(startOfMonth, endOfMonth);

        if (!newBooks.isEmpty()) {
            List<Users> allUsers = userRepository.findAll(); // Lấy tất cả người dùng
            for (Users user : allUsers) {
                Notification notification = new Notification();
                notification.setUserId(Long.valueOf(user.getUserId()));
                notification.setTitle("Sách mới trong tháng!");
                notification.setMessage("Có " + newBooks.size() + " sách mới đã được phát hành trong tháng này. Hãy khám phá ngay!");
                notification.setType(NotificationType.NEW_BOOK.name());
                notification.setCreatedAt(LocalDateTime.now());
                notificationRepository.save(notification);
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
            Notification notification = new Notification();
            notification.setUserId(Long.valueOf(record.getUser().getUserId()));
            notification.setTitle("Sách sắp đến hạn trả!");
            notification.setMessage("Sách '" + record.getBook().getTitle() + "' bạn đang mượn sẽ đến hạn trả vào ngày " + record.getDueDate().toLocalDate() + ".");
            notification.setType(NotificationType.DUE_REMINDER.name());
            notification.setCreatedAt(LocalDateTime.now());
            notificationRepository.save(notification);
        }
    }
}