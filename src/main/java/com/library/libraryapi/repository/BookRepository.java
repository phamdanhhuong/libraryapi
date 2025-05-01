package com.library.libraryapi.repository;

import com.library.libraryapi.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Query("SELECT b FROM Book b WHERE b.publicationDate >= :startDate AND b.publicationDate <= :endDate")
    List<Book> findByPublicationDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT b FROM BorrowingRecord br JOIN br.book b " +
            "WHERE br.borrowDate >= :startDate AND br.borrowDate <= :endDate " +
            "GROUP BY b.bookId ORDER BY COUNT(b.bookId) DESC")
    List<Book> findTopBorrowedBooksInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT b.genre, COUNT(b.genre) FROM Book b GROUP BY b.genre ORDER BY COUNT(b.genre) DESC")
    List<Object[]> findPopularGenres();

    long countByAvailableQuantityGreaterThan(int quantity);

    @Query(value = "SELECT b.* FROM books b JOIN borrowing_records br ON b.book_id = br.book_id GROUP BY b.book_id ORDER BY COUNT(br.book_id) DESC LIMIT 10", nativeQuery = true)
    List<Book> findTop10BorrowedBooks();

    List<Book> findByPrice(int price);

    List<Book> findByPublicationDateAfter(LocalDate date);
}
