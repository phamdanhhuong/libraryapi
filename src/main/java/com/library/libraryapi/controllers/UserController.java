package com.library.libraryapi.controllers;

import java.util.HashMap;
import java.util.Map;

import com.library.libraryapi.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		response.put("avatar", user.getAvatarUrl());
		response.put("phone_number", user.getPhoneNumber().toString());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("check-valid")
	public ResponseEntity<Map<String, Boolean>> CheckValidToken(){
		Map<String, Boolean> response = new HashMap<>();
		response.put("isValid", true);
		return ResponseEntity.ok(response);
	}
	@GetMapping("/total")
	public ResponseEntity<Integer> getTotalUsers() {
		int totalUsers = usersService.getTotalUsers();
		return ResponseEntity.ok(totalUsers);
	}
	@PostMapping("/update")
	public ResponseEntity<ApiResponse> updateInfo(@RequestBody Map<String, String> body){
		String username = body.get("username");
		String phoneNumber = body.get("phone_number");
		String fullName = body.get("full_name");
		boolean result = usersService.updateInfo(username,phoneNumber,fullName);
		if(result){
			ApiResponse response = ApiResponse.builder()
					.message("Update info success!!!!")
					.status(true)
					.data(null)
					.build();
			return ResponseEntity.ok(response);
		}
		ApiResponse response = ApiResponse.builder()
				.message("Update info fail!!!!")
				.status(false)
				.data(null)
				.build();
		return ResponseEntity.ok(response);
	}
	@PostMapping("/avatar_update")
	public ResponseEntity<ApiResponse> avatarUpdate(@RequestBody Map<String, String> body){
		String url = body.get("url");
		String username = body.get("username");
		boolean result = usersService.updateAvatar(url, username);
		if(result){
			ApiResponse response = ApiResponse.builder()
					.message("Update avatar success!!!!")
					.status(true)
					.data(null)
					.build();
			return ResponseEntity.ok(response);
		}
		ApiResponse response = ApiResponse.builder()
				.message("Update avatar fail!!!!")
				.status(false)
				.data(null)
				.build();
		return ResponseEntity.ok(response);
	}
}
