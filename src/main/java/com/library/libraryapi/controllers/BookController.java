package com.library.libraryapi.controllers;

import com.library.libraryapi.dto.*;
import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.Genre;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.services.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;
    @Autowired
    private BookRepository bookRepository;

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

    @GetMapping("/new-in-month")
    public ResponseEntity<List<Book>> getNewBooksInMonth(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month) {
        LocalDate now = LocalDate.now();
        LocalDate startDate = (year == null || month == null) ? now.withDayOfMonth(1) : LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        List<Book> newBooks = bookRepository.findByPublicationDateBetween(startDate, endDate);
        return ResponseEntity.ok(newBooks);
    }
    @GetMapping("/{bookId}/audio-url")
    public ResponseEntity<AudioUrlResponse> getAudioUrl(@PathVariable Integer bookId) {
        Optional<Book> book = bookService.getBookById(bookId);
        if (book.isPresent() && book.get().getAudioUrl() != null) {
            AudioUrlResponse response = new AudioUrlResponse(book.get().getAudioUrl());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/audiobooks")
    public ResponseEntity<ApiResponse> getAllAudioBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        List<Book> audioBooks = allBooks.stream()
                .filter(book -> book.getAudioUrl() != null && !book.getAudioUrl().isEmpty())
                .collect(Collectors.toList());
        ApiResponse response = ApiResponse.builder()
                .message("Audiobooks fetched successfully")
                .status(true)
                .data(audioBooks)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/audiobooks/categories/{genre}")
    public ResponseEntity<ApiResponse> getAudioBooksByGenre(@PathVariable String genre) {
        List<Book> allBooks = bookService.getBooksByGenre(genre);
        List<Book> audioBooksByGenre = allBooks.stream()
                .filter(book -> book.getAudioUrl() != null && !book.getAudioUrl().isEmpty())
                .collect(Collectors.toList());
        ApiResponse response = ApiResponse.builder()
                .message("Audiobooks by genre fetched successfully")
                .status(true)
                .data(audioBooksByGenre)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/audiobooks/recent")
    public ResponseEntity<ApiResponse> getRecentAudioBooks() {
        List<Book> recentBooks = bookService.getRecentBooks();
        List<Book> recentAudioBooks = recentBooks.stream()
                .filter(book -> book.getAudioUrl() != null && !book.getAudioUrl().isEmpty())
                .collect(Collectors.toList());
        ApiResponse response = ApiResponse.builder()
                .message("Recent audiobooks fetched successfully")
                .status(true)
                .data(recentAudioBooks)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/audiobooks/categories")
    public ResponseEntity<List<Genre>> getAudiobookGenres() {
        List<Genre> audiobookGenres = bookService.getAllGenres().stream()
                .filter(genre -> bookService.getBooksByGenre(genre.getGenre()).stream()
                        .anyMatch(book -> book.getAudioUrl() != null && !book.getAudioUrl().isEmpty())) // Kiểm tra có sách Audiobook trong thể loại
                .collect(Collectors.toList());

        return ResponseEntity.ok(audiobookGenres);
    }

    @GetMapping("/ebooks/categories")
    public ResponseEntity<List<Genre>> getEbookCategories() {
        List<Genre> ebookGenres = bookService.getAllGenres().stream()
                .filter(genre -> bookService.getBooksByGenre(genre.getGenre()).stream()
                        .anyMatch(book -> book.getAudioUrl() == null || book.getAudioUrl().isEmpty())) // Kiểm tra có sách Ebook trong thể loại
                .collect(Collectors.toList());

        return ResponseEntity.ok(ebookGenres);
    }
    @GetMapping("/by-category-and-type")
    public ResponseEntity<List<Book>> getBooksByCategoryAndType(
            @RequestParam String category,
            @RequestParam String type) {

        List<Book> books = bookService.getBooksByGenre(category).stream()
                .filter(book -> {
                    boolean isEbook = type.equalsIgnoreCase("ebook");
                    boolean isAudiobook = type.equalsIgnoreCase("audiobook");
                    return (isEbook && (book.getAudioUrl() == null || book.getAudioUrl().isEmpty())) ||
                            (isAudiobook && (book.getAudioUrl() != null && !book.getAudioUrl().isEmpty()));
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(books);
    }
    @GetMapping("/top-borrowed/monthly")
    public ResponseEntity<ApiResponse> getTopBorrowedMonthly(
            @RequestParam(value = "type", defaultValue = "ebook") String type) {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startDate = currentMonth.atDay(1).atStartOfDay(); // Get the start of the day
        LocalDateTime endDate = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Book> topBorrowed = bookRepository.findTopBorrowedBooksInDateRange(startDate, endDate).stream()
                .filter(book -> {
                    boolean isEbook = type.equalsIgnoreCase("ebook");
                    boolean isAudiobook = type.equalsIgnoreCase("audiobook");
                    return (isEbook && (book.getAudioUrl() == null || book.getAudioUrl().isEmpty())) ||
                            (isAudiobook && (book.getAudioUrl() != null && !book.getAudioUrl().isEmpty()));
                })
                .limit(3)
                .collect(Collectors.toList());

        ApiResponse response = ApiResponse.builder()
                .message("Top 3 most borrowed " + type + "s for the current month")
                .status(true)
                .data(topBorrowed)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/analytics/popular-genres")
    public ResponseEntity<ApiResponse> getPopularGenresAnalytics() {
        List<PopularGenre> popularGenres = bookService.getPopularGenres();
        ApiResponse response = ApiResponse.builder()
                .status(true)
                .message("Popular genres fetched successfully")
                .data(popularGenres)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/analytics/available-total-books")
    public ResponseEntity<ApiResponse> getAvailableTotalBooks() {
        long totalBooks = bookRepository.count();
        long availableBooks = bookRepository.countByAvailableQuantityGreaterThan(0);

        AvailableTotalBooksDTO responseData = new AvailableTotalBooksDTO((int) totalBooks, (int) availableBooks);

        ApiResponse response = ApiResponse.builder()
                .status(true)
                .message("Available and total book counts fetched successfully")
                .data(responseData)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/new-releases")
    public ResponseEntity<List<Book>> getNewReleases() {
        List<Book> newReleases = bookService.getNewReleases();
        return ResponseEntity.ok(newReleases);
    }

    @GetMapping("/top-selling")
    public ResponseEntity<List<Book>> getTopSellingBooks() {
        List<Book> topSelling = bookService.getTopSellingBooks();
        return ResponseEntity.ok(topSelling);
    }

    @GetMapping("/recommended")
    public ResponseEntity<List<Book>> getRecommendedBooks() {
        List<Book> recommended = bookService.getRecommendedBooks();
        return ResponseEntity.ok(recommended);
    }

    @GetMapping("/free")
    public ResponseEntity<List<Book>> getFreeBooks() {
        List<Book> freeBooks = bookService.getFreeBooks();
        return ResponseEntity.ok(freeBooks);
    }
    @GetMapping("/ebooks")
    public ResponseEntity<ApiResponse> getAllEBooks() {
        List<Book> eBooks = bookService.getAllEBooks();
        ApiResponse response = ApiResponse.builder()
                .message("Ebooks fetched successfully")
                .status(true)
                .data(eBooks)
                .build();
        return ResponseEntity.ok(response);
    }
}
