package com.library.libraryapi.repository;

import com.library.libraryapi.models.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByBook_BookId(Integer bookId);
    List<Review> findByUser_UserId(Integer userId);
    // Lấy điểm đánh giá trung bình của một cuốn sách
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.book.bookId = :bookId")
    Double findAverageRatingByBookId(Integer bookId);
}
