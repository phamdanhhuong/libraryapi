package com.library.libraryapi.services.impl;

import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.Users;
import com.library.libraryapi.models.WishList;
import com.library.libraryapi.models.WishListBook;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.UsersRepository;
import com.library.libraryapi.repository.WishListBookRepository;
import com.library.libraryapi.repository.WishListRepository;
import com.library.libraryapi.services.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListServiceImpl implements IWishListService {

    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    WishListBookRepository wishListBookRepository;
    @Autowired
    UsersRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<WishList> getWishListByUserId(int userId) {
        List<WishList> reusult = wishListRepository.findByUserUserId(userId);
        return reusult;
    }

    @Override
    public List<Book> getBooksByUserId(int userId) {
        List<WishList> wishLists = wishListRepository.findByUserUserId(userId);
        List<WishListBook> wishListBooks = new ArrayList<>();
        List<Book> result = new ArrayList<>();
        for (int i = 0; i < wishLists.size(); i++) {
            wishListBooks.add(wishListBookRepository.findByWishList(wishLists.get(i)).get(0));
        }
        for (int i = 0; i < wishListBooks.size(); i++) {
            result.add(bookRepository.findById(wishListBooks.get(i).getBook().getBookId()).get());
        }
        return result;
    }

    @Override
    @Transactional
    public String addToWishList(int userId, int idBook) {
        Optional<Users> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found!");
        }
        Optional<Book> book = bookRepository.findById(idBook);
        if (book.isEmpty()) {
            throw new IllegalArgumentException("Book not found!");
        }

        WishList wishList = new WishList().builder()
                .user(user.get())
                .build();
        wishListRepository.save(wishList);

        WishListBook wishListBook = new WishListBook().builder()
                .wishList(wishList)
                .book(book.get())
                .build();
        wishListBookRepository.save(wishListBook);

        return "Add to wish list success !!!";
    }

    @Override
    public String clearWishList() {
        return "";
    }
}
