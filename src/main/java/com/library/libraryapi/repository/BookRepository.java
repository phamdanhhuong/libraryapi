package com.library.libraryapi.repository;

import com.library.libraryapi.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT DISTINCT b.genre FROM Book b WHERE b.genre IS NOT NULL")
    List<String> findAllGenres();
    
    List<Book> findByGenre(String genre);

    @Query("SELECT b FROM Book b ORDER BY b.borrowedCount DESC")
    List<Book> findTop10ByOrderByBorrowedCountDesc();
    
    @Query("SELECT b FROM Book b WHERE b.publicationDate >= :sevenDaysAgo ORDER BY b.publicationDate DESC LIMIT 10")
    List<Book> findTop10RecentBooks(LocalDate sevenDaysAgo);
    
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    List<Book> findByTitleContainingIgnoreCase(String title);
    
//    @Query("SELECT b FROM Book b WHERE " +
//    	       "LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
//    	       "LOWER(b.author) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
//    	       "LOWER(b.description) LIKE LOWER(CONCAT('%', :query, '%'))")
//	List<Book> searchAllFields(String query);

}
