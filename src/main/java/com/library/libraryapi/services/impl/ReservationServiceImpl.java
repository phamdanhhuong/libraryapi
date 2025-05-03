package com.library.libraryapi.services.impl;

import com.library.libraryapi.models.*;
import com.library.libraryapi.repository.*;
import com.library.libraryapi.services.IReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements IReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationBookRepository reservationBookRepository;
    private final BookRepository bookRepository;
    private final UsersRepository userRepository;
    private final WishListRepository wishListRepository;
    private final WishListBookRepository wishListBookRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationBookRepository reservationBookRepository,
                                  BookRepository bookRepository,
                                  UsersRepository userRepository,
                                  WishListRepository wishListRepository,
                                  WishListBookRepository wishListBookRepository  ) {
        this.reservationRepository = reservationRepository;
        this.reservationBookRepository = reservationBookRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
        this.wishListBookRepository = wishListBookRepository;
    }

    @Override
    @Transactional
    public String reserveBooks(Integer userId, List<Integer> bookIds, LocalDateTime expirationDate) {
        Optional<Users> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found!");
        }

        Reservation reservation = Reservation.builder()
                .user(user.get())
                .reservationDate(LocalDateTime.now())
                .expirationDate(expirationDate)
                .status(Reservation.ReservationStatus.PENDING)
                .build();
        reservationRepository.save(reservation);

        List<String> messages = new ArrayList<>();
        for (Integer bookId : bookIds) {
            Optional<Book> book = bookRepository.findById(bookId);
            if (book.isEmpty()) {
                messages.add("Book with ID " + bookId + " not found.");
                continue;
            }

            ReservationBook reservationBook = new ReservationBook().builder()
            		.reservation(reservation)
            		.book(book.get())
            		.build();

            reservationBookRepository.save(reservationBook);

            messages.add("Book with ID " + bookId + " reserved successfully.");
        }

        List<WishList> wishLists = wishListRepository.findByUserUserId(userId);
        List<WishListBook> wishListBooks = wishListBookRepository.findAllByWishListIn(wishLists);
        for(WishListBook wishListBook : wishListBooks){
            if(bookIds.contains(wishListBook.getBook().getBookId())){
                wishListBookRepository.delete(wishListBook);
                wishListRepository.deleteById(wishListBook.getWishList().getWishListId());
            }
        }

        return String.join("\n", messages);
    }

    @Override
    public List<Reservation> getReservationsByUser(Integer userId) {
    	Optional<Users> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found!");
        }
        return reservationRepository.findByUserUserId(userId);
    }
    @Override
    public List<Book> getBooksByReservationId(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        return reservation.getReservationBooks().stream()
                .map(ReservationBook::getBook)
                .collect(Collectors.toList());
    }
    @Override
    public boolean cancelReservation(Integer reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            if (reservation.getStatus() != Reservation.ReservationStatus.CANCELLED &&
                    reservation.getStatus() != Reservation.ReservationStatus.COMPLETED) {
                reservation.setStatus(Reservation.ReservationStatus.CANCELLED); // Use the enum constant
                reservationRepository.save(reservation);
                return true;
            }
        }
        return false;
    }
}
