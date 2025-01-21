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
    public Book updateBook(Long id, Book bookDetails) {
        // Tìm sách theo id
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            
            // Cập nhật các trường thông tin sách
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setGenre(bookDetails.getGenre());
            book.setPublisher(bookDetails.getPublisher());
            book.setPublicationDate(bookDetails.getPublicationDate());
            book.setSummary(bookDetails.getSummary());
            book.setCoverUrl(bookDetails.getCoverUrl());
            book.setAvailableQuantity(bookDetails.getAvailableQuantity());
            book.setTotalQuantity(bookDetails.getTotalQuantity());

            // Lưu lại thông tin sách đã được cập nhật
            return bookRepository.save(book);
        }

        return null; // Nếu không tìm thấy sách, trả về null
    }
    public void deleteBookById(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
        } else {
            throw new RuntimeException("Book with ID " + bookId + " does not exist.");
        }
    }
}
