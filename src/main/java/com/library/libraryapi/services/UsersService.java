package com.library.libraryapi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
	
//	@Autowired
//    private JWTutil jwtUtil;
	
	@Autowired
	private UsersRepository usersRepo;
	
	@Autowired 
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private EmailService emailService;
	
	
	public List<Users> GetAllUsers() {
		return usersRepo.findAll();
	}
	
	public Users GetInfo(String username) {
		Optional<Users> userOptional = usersRepo.findByUsername(username);
	    if (userOptional.isEmpty()) {
	        return null;
	    }
	    return userOptional.get();
	}
	
	public String sendActivationOtp(String email) {
	    Optional<Users> userOptional = usersRepo.findByEmail(email);
	    if (userOptional.isEmpty()) {
	        return "User not found.";
	    }

	    Users user = userOptional.get();
	    String otp = generateOtp();
	    user.setOtp(otp);
	    user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));
	    usersRepo.save(user);

	    String subject = "Account Activation OTP";
	    String body = "Your OTP for account activation is: " + otp;
	    emailService.sendOtpEmail(email, subject, body);

	    return "OTP sent to email.";
	}
	
	public String activateAccount(String email, String otp) {
	    Optional<Users> userOptional = usersRepo.findByEmail(email);
	    if (userOptional.isEmpty()) {
	        return "User not found.";
	    }

	    Users user = userOptional.get();
	    if (user.getOtp().equals(otp) && user.getOtpExpiry().isAfter(LocalDateTime.now())) {
	        user.setActive(true);
	        user.setOtp(null);
	        user.setOtpExpiry(null);
	        usersRepo.save(user);
	        return "Account activated successfully.";
	    }
	    return "Invalid or expired OTP.";
	}
	
	public String sendPasswordResetOtp(String email) {
	    Optional<Users> userOptional = usersRepo.findByEmail(email);
	    if (userOptional.isEmpty()) {
	        return "User not found.";
	    }

	    Users user = userOptional.get();
	    String otp = generateOtp();
	    user.setOtp(otp);
	    user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));
	    usersRepo.save(user);

	    String subject = "Password Reset OTP";
	    String body = "Your OTP for password reset is: " + otp;
	    emailService.sendOtpEmail(email, subject, body);

	    return "Password reset OTP sent to email.";
	}
	
	public String resetPassword(String email, String otp, String newPassword) {
	    Optional<Users> userOptional = usersRepo.findByEmail(email);
	    if (userOptional.isEmpty()) {
	        return "User not found.";
	    }

	    Users user = userOptional.get();
	    if (user.getOtp().equals(otp) && user.getOtpExpiry().isAfter(LocalDateTime.now())) {
	        user.setPassword(passwordEncoder.encode(newPassword)); // Encrypt password here
	        user.setOtp(null);
	        user.setOtpExpiry(null);
	        usersRepo.save(user);
	        return "Password reset successfully.";
	    }
	    return "Invalid or expired OTP.";
	}
	
	private String generateOtp() {
	    return String.valueOf(new Random().nextInt(900000) + 100000); 
	}

	public int getTotalUsers() {
		return (int) usersRepo.count();
	}
}
