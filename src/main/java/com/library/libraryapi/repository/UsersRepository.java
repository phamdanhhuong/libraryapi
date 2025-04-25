package com.library.libraryapi.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.libraryapi.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
	Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    Optional<Users> findById(Integer userId);
}
