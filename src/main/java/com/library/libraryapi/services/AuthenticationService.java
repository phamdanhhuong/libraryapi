package com.library.libraryapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.libraryapi.dto.LoginRequest;
import com.library.libraryapi.dto.RegisterRequest;
import com.library.libraryapi.models.Users;
import com.library.libraryapi.repository.UsersRepository;

@Service
public class AuthenticationService {
	@Autowired
	private UsersRepository usersRepo;
	
	@Autowired 
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public Users authenticate(LoginRequest loginReq) {
        authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword())
        );
        //System.out.println("user authenticated "+loginReq.getUsername());
        
        Optional<Users> userOpt = usersRepo.findByEmail(loginReq.getUsername());
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return usersRepo.findByUsername(loginReq.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
      
    }
	
	public Users Register(RegisterRequest registerReq) {
		
		if (usersRepo.findByUsername(registerReq.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

//        if (usersRepo.findByEmail(registerReq.getEmail()).isPresent()) {
//            throw new RuntimeException("Email already exists");
//        }
        
        Users user = Users.builder()
                .email(registerReq.getEmail())
                .fullName(registerReq.getFull_name())
                .username(registerReq.getUsername())
                .password(passwordEncoder.encode(registerReq.getPassword()))
                .phoneNumber(registerReq.getPhone_number())
                .build();
        
		return  usersRepo.save(user);
	}
	
	
}
