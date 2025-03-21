package com.library.libraryapi.services.impl;

import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.Genre;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.GenreRepository;
import com.library.libraryapi.services.IBookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public List<Book> getTop10BorrowedBooks() {
        return bookRepository.findTop10ByOrderByBorrowedCountDesc();
    }

    @Override
    public List<Book> getRecentBooks() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        return bookRepository.findTop10RecentBooks(sevenDaysAgo);
    }
}
