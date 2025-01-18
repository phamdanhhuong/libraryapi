package com.library.libraryapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.libraryapi.dto.LoginRequest;
import com.library.libraryapi.dto.RegisterRequest;
import com.library.libraryapi.models.Users;
import com.library.libraryapi.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepo;
	
	@Autowired 
	private PasswordEncoder passwordEncoder; 
	
	
	public List<Users> GetAllUsers() {
		return usersRepo.findAll();
	}
	
	public Users Register(RegisterRequest registerReq) {
		
		if (usersRepo.findByUsername(registerReq.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

//        if (usersRepo.findByEmail(registerReq.getEmail()).isPresent()) {
//            throw new RuntimeException("Email already exists");
//        }
        
        Users user = new Users();
        user.setEmail(registerReq.getEmail());
        user.setFullName(registerReq.getUsername());
        user.setUsername(registerReq.getUsername());
        user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        user.setPhoneNumber(registerReq.getPhone_number());

		return  usersRepo.save(user);
	}
	public Users Login(LoginRequest loginReq) {
        // Tìm user bằng username hoặc email
        Optional<Users> userOpt = usersRepo.findByUsername(loginReq.getUsername())
                                           .or(() -> usersRepo.findByEmail(loginReq.getUsername()));

        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Users user = userOpt.get();

        // Kiểm tra mật khẩu
        if (!user.getPassword().equals(loginReq.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
