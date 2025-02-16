package com.library.libraryapi.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class Users implements UserDetails{
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
    
	@Override
	public boolean isEnabled() {
		return true;
	}
}
