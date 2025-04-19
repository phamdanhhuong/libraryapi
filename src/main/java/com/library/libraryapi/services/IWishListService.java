package com.library.libraryapi.services;

import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.WishList;

import java.util.List;

public interface IWishListService {
    List<WishList> getWishListByUserId(int userId);
    List<Book> getBooksByUserId(int userId);
    String addToWishList(int userId, int idBook);
    String clearWishList();
}
