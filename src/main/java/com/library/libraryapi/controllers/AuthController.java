package com.library.libraryapi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryapi.dto.ApiResponse;
import com.library.libraryapi.dto.LoginRequest;
import com.library.libraryapi.dto.LoginResponse;
import com.library.libraryapi.dto.OtpRequest;
import com.library.libraryapi.dto.RegisterRequest;
import com.library.libraryapi.dto.ResetPassRequest;
import com.library.libraryapi.models.Users;
import com.library.libraryapi.services.AuthenticationService;
import com.library.libraryapi.services.JwtService;
import com.library.libraryapi.services.UsersService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	@Autowired
	private UsersService usersService; 
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> Register(@RequestBody RegisterRequest registerReq){
		try {
			authenticationService.Register(registerReq);
		} catch (Exception e) {
			ApiResponse response = ApiResponse.builder()
					.message("Fail to create account !!!")
					.status(false)
					.data(null)
					.build();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		ApiResponse response = ApiResponse.builder()
				.message("Register success !!!")
				.status(true)
				.data(null)
				.build();
        return ResponseEntity.ok(response);
	}
	
	@PostMapping("/send-activation-otp")
	public ResponseEntity<ApiResponse> sendActivationOtp(@RequestBody Map<String, String> requestBody ) {
	    String message = usersService.sendActivationOtp(requestBody.get("email"));
	    ApiResponse response = ApiResponse.builder()
				.message(message)
				.status(true)
				.data(null)
				.build();
	    return ResponseEntity.ok(response);
	}

	@PostMapping("/activate-account")
	public ResponseEntity<ApiResponse> activateAccount(@RequestBody OtpRequest requestBody) {
	    String message = usersService.activateAccount(requestBody.getEmail(), requestBody.getOtp());
	    ApiResponse response = ApiResponse.builder()
				.message(message)
				.status(true)
				.data(null)
				.build();
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/send-password-reset-otp")
	public ResponseEntity<ApiResponse> sendPasswordResetOtp(@RequestBody Map<String, String> requestBody) {
//	    String response = usersService.sendPasswordResetOtp(requestBody.get("email"));
		String message = usersService.sendPasswordResetOtp(requestBody.get("email"));
		ApiResponse response = ApiResponse.builder()
				.message(message)
				.status(true)
				.data(null)
				.build();
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<ApiResponse> resetPassword(@RequestBody ResetPassRequest requestBody) {
	    String message = usersService.resetPassword(requestBody.getEmail(), requestBody.getOtp(), requestBody.getNewPassword());
		ApiResponse response = ApiResponse.builder()
				.message(message)
				.status(true)
				.data(null)
				.build();
	    return ResponseEntity.ok(response);
	}
	
	
	@PostMapping("/login")
	@Transactional
	public ResponseEntity<LoginResponse> Login(@RequestBody LoginRequest requestBody){
		Users authenticatedUser = authenticationService.authenticate(requestBody);
		String jwtToken = jwtService.generateToken(authenticatedUser);
		
		new LoginResponse();
		LoginResponse response = LoginResponse.builder()
				.token(jwtToken)
				.expiresIn(jwtService.getExpirationTime())
				.build();
		
        return ResponseEntity.ok(response);
	}
	
	
}
