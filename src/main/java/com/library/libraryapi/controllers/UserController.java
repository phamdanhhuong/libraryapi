package com.library.libraryapi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryapi.models.Users;
import com.library.libraryapi.services.UsersService;

@RestController
@RequestMapping(value = "/info")
public class UserController {
	@Autowired
	private UsersService usersService; 
	
	@GetMapping("{username}")
	public ResponseEntity<Map<String, String>> GetInfo(@PathVariable String username){
		Users user = usersService.GetInfo(username);
		Map<String, String> response =new HashMap<>();
		response.put("id", user.getUserId().toString());
		response.put("full_name", user.getFullName());
		response.put("email", user.getEmail());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("check-valid")
	public ResponseEntity<Map<String, Boolean>> CheckValidToken(){
		Map<String, Boolean> response = new HashMap<>();
		response.put("isValid", true);
		return ResponseEntity.ok(response);
	}
}
