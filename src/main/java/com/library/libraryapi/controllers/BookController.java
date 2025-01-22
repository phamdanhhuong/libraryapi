package com.library.libraryapi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryapi.models.Book;
import com.library.libraryapi.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Integer bookId) {
        Optional<Book> book = bookService.getBookById(bookId);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.status(404).body("Book not found");
        }
    }
}