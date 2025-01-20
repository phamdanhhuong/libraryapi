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
import com.library.libraryapi.utils.JWTutil;

@Service
public class UsersService {
	
	@Autowired
    private JWTutil jwtUtil;
	
	@Autowired
	private UsersRepository usersRepo;
	
	@Autowired 
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private EmailService emailService;
	
	
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
	
	public String Login(LoginRequest loginReq) {
        Optional<Users> userOpt = usersRepo.findByUsername(loginReq.getUsername())
                                           .or(() -> usersRepo.findByEmail(loginReq.getUsername()));

        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Users user = userOpt.get();

        boolean auth =passwordEncoder.matches(loginReq.getPassword(), user.getPassword());
        // Kiểm tra mật khẩu
        if (!auth) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername());	
    }
}
