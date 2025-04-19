package com.library.libraryapi.repository;

import com.library.libraryapi.models.Users;
import com.library.libraryapi.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Integer> {
    List<WishList> findByUser(Users user);
    List<WishList> findByUserUserId(Integer userId);
}
