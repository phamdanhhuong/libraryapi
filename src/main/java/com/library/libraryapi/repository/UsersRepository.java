package com.library.libraryapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.libraryapi.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

}
