package com.library.libraryapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.libraryapi.models.Book;
import com.library.libraryapi.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }
}
