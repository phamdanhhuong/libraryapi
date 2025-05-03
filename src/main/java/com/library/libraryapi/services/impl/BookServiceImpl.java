package com.library.libraryapi.services.impl;

import com.library.libraryapi.dto.PopularGenre;
import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.Genre;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.GenreRepository;
import com.library.libraryapi.services.IBookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService{
	private final BookRepository bookRepository;
	private final GenreRepository genreRepository;

	public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository) {
	    this.bookRepository = bookRepository;
	    this.genreRepository = genreRepository;
	}


    @Override
    public Optional<Book> getBookById(Integer bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public List<Genre> getAllGenres() {
        //return bookRepository.findAllGenres();
    	return genreRepository.findAll();
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    	
    }
    @Override
    public List<Book> findBooksByAuthor(String authorName) {
        return bookRepository.findByAuthorContainingIgnoreCase(authorName);
    }
    
    @Override
    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingIgnoreCase(query);
    }

    @Override
    public List<Book> getTop10BorrowedBooks() {
        return bookRepository.findTop10ByOrderByBorrowedCountDesc();
    }

    @Override
    public List<Book> getRecentBooks() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        return bookRepository.findTop10RecentBooks(sevenDaysAgo);
    }


	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

    public List<PopularGenre> getPopularGenres() {
        List<Object[]> results = bookRepository.findPopularGenres();
        return results.stream()
                .map(result -> new PopularGenre((String) result[0], ((Long) result[1]).intValue()))
                .limit(3) // Limit to the top 3
                .collect(Collectors.toList());
    }
    @Override
    public List<Book> getNewReleases() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(12);
        return bookRepository.findByPublicationDateAfter(oneMonthAgo);
    }

    @Override
    public List<Book> getTopSellingBooks() {
        return bookRepository.findTop10BorrowedBooks();
    }

    @Override
    public List<Book> getRecommendedBooks() {
        LocalDate twelveMonthsAgo = LocalDate.now().minusMonths(12);
        return bookRepository.findAll().stream()
                .filter(book -> book.getPublicationDate().isAfter(twelveMonthsAgo))
                .sorted((b1, b2) -> Integer.compare(b2.getBorrowedCount(), b1.getBorrowedCount()))
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getFreeBooks() {
        return bookRepository.findByPrice(0);
    }
    @Override
    public List<Book> getAllEBooks() {
        List<Book> allBooks = bookRepository.findAll();
        return allBooks.stream()
                .filter(book -> book.getAudioUrl() == null || book.getAudioUrl().isEmpty())
                .collect(Collectors.toList());
    }
}
