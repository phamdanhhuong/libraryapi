package com.library.libraryapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.libraryapi.dto.ReviewRequest;
import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.Review;
import com.library.libraryapi.models.Users;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.ReviewRepository;
import com.library.libraryapi.repository.UsersRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // Tạo một đánh giá mới
    public Review createReview(ReviewRequest reviewDto) {
        // Lấy User và Book từ cơ sở dữ liệu
        Users user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(reviewDto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Tạo và lưu đánh giá
        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());

        return reviewRepository.save(review);
    }
    public List<Review> getReviewsByBookId(Integer bookId) {
        return reviewRepository.findByBook_BookId(bookId);
    }
    public List<Review> getReviewsByUserId(Integer userId) {
        return reviewRepository.findByUser_UserId(userId);
    }
    public Double getAverageRating(Integer bookId) {
        Double averageRating = reviewRepository.findAverageRatingByBookId(bookId);
        return (averageRating != null) ? Math.round(averageRating * 10.0) / 10.0 : 0.0;
    }
    public void updateBookRating(Book book) {
        List<Review> reviews = reviewRepository.findByBook(book);
        double avg = reviews.stream()
                            .mapToDouble(Review::getRating)
                            .average()
                            .orElse(0.0);
        book.setRating(avg);
        bookRepository.save(book);
    }

}
