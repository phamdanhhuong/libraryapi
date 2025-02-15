package com.library.libraryapi.repository;

import com.library.libraryapi.models.Reservation;
import com.library.libraryapi.models.ReservationBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationBookRepository extends JpaRepository<ReservationBook, Integer> {
    List<ReservationBook> findByReservation(Reservation reservation);
}
