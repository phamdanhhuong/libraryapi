package com.library.libraryapi.repository;

import com.library.libraryapi.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT DISTINCT b.genre FROM Book b WHERE b.genre IS NOT NULL")
    List<String> findAllGenres();
    
    List<Book> findByGenre(String genre);

    @Query("SELECT b FROM Book b ORDER BY b.borrowedCount DESC")
    List<Book> findTop10ByOrderByBorrowedCountDesc();
}
