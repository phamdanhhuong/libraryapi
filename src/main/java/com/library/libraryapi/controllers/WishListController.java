package com.library.libraryapi.controllers;

import com.library.libraryapi.dto.ApiResponse;
import com.library.libraryapi.dto.ReservationRequest;
import com.library.libraryapi.dto.WishListRequest;
import com.library.libraryapi.models.Book;
import com.library.libraryapi.models.WishList;
import com.library.libraryapi.services.IReservationService;
import com.library.libraryapi.services.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    private final IWishListService wishListService;

    public WishListController(IWishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> reserveBooks(@RequestBody WishListRequest wishListRequest) {
        try {
            String message = wishListService.addToWishList(
                    wishListRequest.getUser_id(),
                    wishListRequest.getBook_id()
            );
            ApiResponse response = ApiResponse.builder()
                    .message(message)
                    .status(true)
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            ApiResponse response = ApiResponse.builder()
                    .message(e.getMessage())
                    .status(false)
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("{userId}")
    public ResponseEntity<ApiResponse> getWishList(@PathVariable int userId){
        List<Book> result = wishListService.getBooksByUserId(userId);
        ApiResponse response = ApiResponse.builder()
                .message("Reservations fetched successfully")
                .status(true)
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
