package com.library.libraryapi.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class Users {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name = "username", nullable = false, length = 50)
	private String username;
	
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "phone_number")
	private Long phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "is_active", nullable = false)
	private boolean isActive = false;

	@Column(name = "otp")
	private String otp;

	@Column(name = "otp_expiry")
	private LocalDateTime otpExpiry;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpExpiry() {
		return otpExpiry;
	}

	public void setOtpExpiry(LocalDateTime otpExpiry) {
		this.otpExpiry = otpExpiry;
	}

	public Users() {}

	public Users(Integer userId, String username, String password, String fullName, Long phoneNumber, String email,
			boolean isActive, String otp, LocalDateTime otpExpiry) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.isActive = isActive;
		this.otp = otp;
		this.otpExpiry = otpExpiry;
	}

    
}
