package com.library.libraryapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryapi.models.Users;
import com.library.libraryapi.services.UsersService;

@RestController
@RequestMapping(value = "/")
public class HomeController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping
	public String welcome() {
		return "Welcome to library";
	}
	@GetMapping("/allusers")
	public List<Users> AllUsers() {
		return usersService.GetAllUsers();
	}
}
