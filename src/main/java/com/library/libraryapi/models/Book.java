package com.library.libraryapi.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "author", nullable = false, length = 255)
    private String author;

    @Column(name = "genre", length = 100)
    private String genre;

    @Column(name = "publisher", length = 255)
    private String publisher;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "summary", columnDefinition = "TEXT") 
    private String summary;

    @Column(name = "cover_url", length = 255)
    private String coverUrl;

    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;

    @Column(name = "total_quantity", nullable = false)
    private int totalQuantity;
    
    @Column(name = "borrowed_count", nullable = false)
    private int borrowedCount;
    
    @Column(name = "rating", nullable = false)
    private double rating = 0.0;
    
    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "audio_url", length = 255)
    private String audioUrl;

    @Column(name = "audio_duration")
    private Integer audioDuration;

}
