package com.hms_hospital_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.User;
import com.hms_hospital_management_system.repository.UserRepository;

@Service
public class UserService {
	

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    

	//-------------------------------- Register user---------------------------------------
    public ResponseEntity<User> register(User user) {
         User u=userRepository.save(user);
         return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }
    // --------------------------------------Login-----------------------------
    public ResponseEntity<?> login(String username,String password) {
    	User user=userRepository.findByUserName(username);
    	if(user !=null && user.getPassword().equals(password)) {
    		return ResponseEntity.ok(user);
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Enter valid user name or password");
    	}
    }
	
	

}
