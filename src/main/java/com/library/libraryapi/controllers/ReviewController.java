package com.library.libraryapi.controllers;

import com.library.libraryapi.dto.ReviewRequest;
import com.library.libraryapi.models.Review;
import com.library.libraryapi.services.ReviewService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // API để tạo đánh giá
    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest review) {
    	Review savedReview = reviewService.createReview(review);
        return ResponseEntity.ok(savedReview);
    }
    // API lấy tất cả đánh giá của một cuốn sách
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable Integer bookId) {
        List<Review> reviews = reviewService.getReviewsByBookId(bookId);
        return ResponseEntity.ok(reviews);
    }
    // API lấy tất cả đánh giá của một người dùng
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Integer userId) {
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        return ResponseEntity.ok(reviews);
    }
    // API lấy điểm đánh giá trung bình của một cuốn sách
    @GetMapping("/book/{bookId}/average")
    public ResponseEntity<?> getAverageRating(@PathVariable Integer bookId) {
        Double averageRating = reviewService.getAverageRating(bookId);
        return ResponseEntity.ok("{\"bookId\": " + bookId + ", \"averageRating\": " + averageRating + "}");
    }
}
