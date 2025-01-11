package com.library.libraryapi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryapi.dto.RegisterRequest;
import com.library.libraryapi.services.UsersService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	@Autowired
	private UsersService usersService; 
	@PostMapping
	public Map<String, String> Register(@RequestBody RegisterRequest registerReq){
		//System.out.println(registerReq.getFull_name());
		try {
			usersService.Register(registerReq);
		} catch (Exception e) {
			Map<String, String> response = new HashMap<>();
	        response.put("error", e.getMessage());
	        return response;
		}
		Map<String, String> response = new HashMap<>();
        response.put("message", "ok");
        return response;
	}
	@GetMapping
	public Map<String, String> Test(){
		Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, JSON response!");
        return response;
        
	}
}
