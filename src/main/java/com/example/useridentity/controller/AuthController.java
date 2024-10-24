package com.example.useridentity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.useridentity.service.UserService;
import com.example.useridentitypayload.UserDao;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;


	//POST store the user in DB
	@PostMapping("/register")
	public ResponseEntity<UserDao>  createUser(@RequestBody UserDao userDao)
	{
		return new ResponseEntity<UserDao>(userService.createUser(userDao),HttpStatus.CREATED);
	}

	
	

}
