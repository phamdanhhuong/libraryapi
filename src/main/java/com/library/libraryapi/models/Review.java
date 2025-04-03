package com.library.libraryapi.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;  // Giả sử có một lớp User để đại diện cho người dùng

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;  // Liên kết với bảng Book

    @Column(name = "rating", nullable = false)
    private double rating;  // Số sao đánh giá

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;  // Bình luận của người dùng

}
