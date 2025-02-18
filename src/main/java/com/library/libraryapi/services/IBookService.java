package com.library.libraryapi.services;

import com.library.libraryapi.models.Book;
import java.util.List;
import java.util.Optional;

public interface IBookService {
    Optional<Book> getBookById(Integer bookId);
    List<String> getAllGenres();
    List<Book> getBooksByGenre(String genre);
    List<Book> getTop10BorrowedBooks();
    List<Book> getRecentBooks();
}
