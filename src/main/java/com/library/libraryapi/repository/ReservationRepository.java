package com.library.libraryapi.repository;

import com.library.libraryapi.models.Reservation;
import com.library.libraryapi.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	List<Reservation> findByUser(Users user);
	List<Reservation> findByUserUserId(Integer userId);
}
