package com.library.libraryapi.repository;

import com.library.libraryapi.models.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByUserUserId(Integer userId);
}
