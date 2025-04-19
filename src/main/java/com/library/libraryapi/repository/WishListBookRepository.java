package com.library.libraryapi.repository;

import com.library.libraryapi.models.WishList;
import com.library.libraryapi.models.WishListBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListBookRepository extends JpaRepository<WishListBook, Integer> {
    List<WishListBook> findByWishList(WishList wishList);
}
