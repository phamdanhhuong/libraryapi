package com.library.libraryapi.controllers;

import com.library.libraryapi.dto.ApiResponse;
import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.Genre;
import com.library.libraryapi.services.impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse> getBookById(@PathVariable Integer bookId) {
        Optional<Book> book = bookService.getBookById(bookId);
        if (book.isPresent()) {
            ApiResponse response = ApiResponse.builder()
    				.message("Book found")
    				.status(true)
    				.data(book.get())
    				.build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            ApiResponse response = new ApiResponse(false, "Book not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

//    @GetMapping("/categories")
//    public ResponseEntity<ApiResponse> getAllGenres() {
//        List<Genre> genres = bookService.getAllGenres();
//        ApiResponse response = ApiResponse.builder()
//				.message("Genres fetched successfully")
//				.status(true)
//				.data(genres)
//				.build();
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
    @GetMapping("/categories")
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = bookService.getAllGenres();
        return ResponseEntity.status(HttpStatus.OK).body(genres);
    }

//    @GetMapping("/categories/{genre}")
//    public ResponseEntity<ApiResponse> getBooksByGenre(@PathVariable String genre) {
//        List<Book> books = bookService.getBooksByGenre(genre);
//        ApiResponse response = ApiResponse.builder()
//				.message("Books fetched successfully")
//				.status(true)
//				.data(books)
//				.build();
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
    @GetMapping("/categories/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre) {
        List<Book> books = bookService.getBooksByGenre(genre);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
    
    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String authorName) {
        List<Book> books = bookService.findBooksByAuthor(authorName);
        return books.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(books);
    }
    
    @GetMapping("/top-borrowed")
    public ResponseEntity<ApiResponse> getTopBorrowedBooks() {
        List<Book> books = bookService.getTop10BorrowedBooks();
        ApiResponse response = ApiResponse.builder()
				.message("Top borrowed books fetched successfully")
				.status(true)
				.data(books)
				.build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @GetMapping("/recent")
//    public ResponseEntity<ApiResponse> getRecentBooks() {
//        List<Book> books = bookService.getRecentBooks();
//        ApiResponse response = ApiResponse.builder()
//				.message("Recent books fetched successfully")
//				.status(true)
//				.data(books)
//				.build();
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
    @GetMapping("/recent")
    public ResponseEntity<List<Book>> getRecentBooks() {
        List<Book> books = bookService.getRecentBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("query") String query) {
        return bookService.searchBooks(query);
    }
}
