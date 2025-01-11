package com.library.libraryapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.libraryapi.models.Users;
import com.library.libraryapi.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepo;
	
	public List<Users> GetAllUsers() {
		return usersRepo.findAll();
	}
}
