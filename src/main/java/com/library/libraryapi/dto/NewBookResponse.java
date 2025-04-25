package com.library.libraryapi.dto;

import com.library.libraryapi.models.Book;
import lombok.Data;

import java.util.List;

@Data
public class NewBookResponse {
    private List<Book> newBooks;
}