package com.library.libraryapi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryapi.dto.LoginRequest;
import com.library.libraryapi.dto.OtpRequest;
import com.library.libraryapi.dto.RegisterRequest;
import com.library.libraryapi.dto.ResetPassRequest;
import com.library.libraryapi.services.UsersService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	@Autowired
	private UsersService usersService; 
	@PostMapping("/register")
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
	
	@PostMapping("/send-activation-otp")
	public ResponseEntity<String> sendActivationOtp(@RequestBody Map<String, String> requestBody ) {
	    String response = usersService.sendActivationOtp(requestBody.get("email"));
	    return ResponseEntity.ok(response);
	}

	@PostMapping("/activate-account")
	public ResponseEntity<String> activateAccount(@RequestBody OtpRequest requestBody) {
	    String response = usersService.activateAccount(requestBody.getEmail(), requestBody.getOtp());
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/send-password-reset-otp")
	public ResponseEntity<String> sendPasswordResetOtp(@RequestBody Map<String, String> requestBody) {
	    String response = usersService.sendPasswordResetOtp(requestBody.get("email"));
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPassRequest requestBody) {
	    String response = usersService.resetPassword(requestBody.getEmail(), requestBody.getOtp(), requestBody.getNewPassword());
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public Map<String, String> Login(@RequestBody LoginRequest requestBody){
		Map<String, String> response = new HashMap<>();
		String token = usersService.Login(requestBody);
        response.put("token", token);
        return response;
	}
}
