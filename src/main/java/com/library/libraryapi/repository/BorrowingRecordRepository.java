package com.library.libraryapi.repository;

import java.util.List;

import com.library.libraryapi.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.BorrowingRecord;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {

    @Query("SELECT br.book FROM BorrowingRecord br GROUP BY br.book ORDER BY COUNT(br.book) DESC")
    List<Book> findTop10MostBorrowedBooks();
    List<BorrowingRecord> findByUser(Users user);
}
