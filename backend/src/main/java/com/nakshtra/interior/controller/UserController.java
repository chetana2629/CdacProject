package com.nakshtra.interior.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakshtra.interior.customexcpetion.UserNotFoundException;
import com.nakshtra.interior.dto.ErrorResponse;
import com.nakshtra.interior.entity.User;
import com.nakshtra.interior.entity.UserLogin;
import com.nakshtra.interior.service.UserService;


@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

		@Autowired
		private UserService userService ;

		@PostMapping("/login")
		public ResponseEntity<?> login(@RequestBody UserLogin loginRequest) {
		    System.out.println("Username: " + loginRequest.getUsername());
		    System.out.println("Password: " + loginRequest.getPassword());

		    // Fetch user by username (case-insensitive)
		    User user = userService.fetchUserByUserName(loginRequest.getUsername().toLowerCase());

		    if (user != null) {
		        System.out.println("User found: " + user.getUserName());

		        // Verify password (consider using password hashing in production)
		        if (user.getPassword().equals(loginRequest.getPassword())) {
		            // Create a response object including user role
		            Map<String, Object> response = new HashMap<>();
		            response.put("message", "Login successful");
		            response.put("role", user.getRole()); // Add user role to response
		            return ResponseEntity.ok(response);
		        } else {
		            return ResponseEntity.status(401).body("Incorrect password");
		        }
		    } else {
		        System.out.println("User not found");
		        return ResponseEntity.status(401).body("User not found");
		    }
		}


		@PostMapping("/add")
		public ResponseEntity<?> registerdUser(@RequestBody User user) {
			User createdUser = userService.create(user);
			return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		}

		@GetMapping("/all")
		public ResponseEntity<?> getAllUserser() {
			try {
				return new ResponseEntity<>(userService.fetchAll(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>
				(new ErrorResponse("User Fetching is failed", e.getMessage()),
						HttpStatus.BAD_REQUEST);
			}

		}
		@GetMapping("/getuser/{userId}")
		public ResponseEntity<?> getUserById(@PathVariable("userId") Integer id) {
			try {
				return  ResponseEntity.ok(userService.fetchById(id));
				
			} catch (Exception e) {
				
				ErrorResponse errorResponce=
			    new ErrorResponse("User Fetching is failed", e.getMessage());
				return new ResponseEntity<>
				(errorResponce,HttpStatus.BAD_REQUEST);
			}
		}

		@PutMapping("/update/{userId}")
		public ResponseEntity<?> updateuserById(@PathVariable("userId") Integer id,
				@RequestBody User updateUser) {
			try {
				User existingUser=userService.fetchById(id);
				
				return  ResponseEntity.ok(userService.update(updateUser, existingUser));
				
			} catch (Exception e) {
				
				ErrorResponse errorResponce= new ErrorResponse("User updation is failed", e.getMessage());
				return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
			}
		}
		@DeleteMapping("/delete/{userId}")
		public ResponseEntity<?>deleteUserById(@PathVariable ("userId") Integer id)
		{ try {
			User existingUser=userService.fetchById(id);
			return ResponseEntity.ok(userService.delete(existingUser));
					
		} catch (Exception e) {

			ErrorResponse errorResponce= new ErrorResponse("User deletion is failead", e.getMessage());
			return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
			
		}
			
		}
		@GetMapping("/getbyName/{name}")
		public ResponseEntity<?> getUserByUserName(@PathVariable String name){
			try {
				ResponseEntity res = null;
				User foundUser = userService.fetchUserByUserName(name);
				if(foundUser != null) {
					return res.ok(foundUser);
				
				}else {
					throw new UserNotFoundException("Invalid Username..");
				}
			
			} catch (Exception e) {
				return new ResponseEntity<>(new ErrorResponse("fetching user by username failed.", e.getMessage()),HttpStatus.BAD_REQUEST);
			}
			
			
		}
		
}
