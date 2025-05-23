package com.library.libraryapi.services;

import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.Genre;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Optional<Book> getBookById(Integer bookId);
    List<Genre> getAllGenres();
    List<Book> getBooksByGenre(String genre);
    List<Book> getAllBooks();
    List<Book> getTop10BorrowedBooks();
    List<Book> getRecentBooks();
    List<Book> findBooksByAuthor(String authorName);
    List<Book> searchBooks(String query);

    List<Book> getNewReleases();

    List<Book> getTopSellingBooks();

    List<Book> getRecommendedBooks();

    List<Book> getFreeBooks();

    List<Book> getAllEBooks();
}
