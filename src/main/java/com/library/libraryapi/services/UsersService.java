package com.library.libraryapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.libraryapi.dto.RegisterRequest;
import com.library.libraryapi.models.Users;
import com.library.libraryapi.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepo;
	
	public List<Users> GetAllUsers() {
		return usersRepo.findAll();
	}
	
	public Users Register(RegisterRequest registerReq) {
		if (usersRepo.findByUsername(registerReq.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (usersRepo.findByEmail(registerReq.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        
        Users user = new Users();
        user.setEmail(registerReq.getEmail());
        user.setFullName(registerReq.getUsername());
        user.setUsername(registerReq.getUsername());
        user.setPassword(registerReq.getPassword());
        user.setPhoneNumber(registerReq.getPhone_number());

		return  usersRepo.save(user);
	}
}
