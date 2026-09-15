package com.hms_hospital_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hms_hospital_management_system.entity.User;
import com.hms_hospital_management_system.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService service;
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}


	/*
	 *----------------REGISTER--------------------Controller
	 */
	
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
	   return service.register(user);
	}
	
	/*
	 *----------------------LOGIN-----------------------service
	 */
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String username,@RequestParam String password){
		
		return service.login(username, password);
	}
}
