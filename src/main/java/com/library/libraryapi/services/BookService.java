package com.library.libraryapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.libraryapi.models.Book;
import com.library.libraryapi.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> getBookById(Integer bookId) {
        return bookRepository.findById(bookId);
    }
    public List<String> getAllGenres() {
        return bookRepository.findAllGenres();
    }
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
    public List<Book> getTop10BorrowedBooks() {
        return bookRepository.findTop10ByOrderByBorrowedCountDesc();
    }

}
