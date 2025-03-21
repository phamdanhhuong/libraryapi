package com.library.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.libraryapi.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{
	
}
