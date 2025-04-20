package com.library.libraryapi.repository;

import com.library.libraryapi.models.WishList;
import com.library.libraryapi.models.WishListBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishListBookRepository extends JpaRepository<WishListBook, Integer> {
    List<WishListBook> findByWishList(WishList wishList);
    List<WishListBook> findAllByWishListIn(List<WishList> wishLists);

}
