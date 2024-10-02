package com.nakshtra.interior.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakshtra.interior.customexcpetion.UserNotFoundException;
import com.nakshtra.interior.dao.UserRepo;
import com.nakshtra.interior.entity.User;

@Service

public class UserService implements CurdService<User,Integer>{

	@Autowired
	private UserRepo userRepo;

	@Override
	public User create(User user) {
		
		return userRepo.save(user);
	}

	@Override
	public List<User> fetchAll() {
		
		return userRepo.findAll();
	}

	@Override
	public User fetchById(Integer id) {
	
		return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid id"));
	}

	@Override
	public User update(User updatedUser, User existingUser) {
		existingUser.setPassword(updatedUser.getPassword());
		existingUser.setFirstName(updatedUser.getFirstName());
		existingUser.setLastName(updatedUser.getLastName());
		existingUser.setPhoneNo(updatedUser.getPhoneNo());
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setRole(updatedUser.getRole());
		existingUser.setState(updatedUser.getState());
		existingUser.setPincode(updatedUser.getPincode());
		existingUser.setCity(updatedUser.getCity());
		
		return userRepo.save(existingUser);
	}

	@Override
	public String delete(User user) {
		userRepo.delete(user);
		return user.getId() + " deleted";
	}
	
	public User fetchUserByUserName(String name) {
	    return userRepo.findByUserNameIgnoreCase(name); // Use the case-insensitive query
	}

	
	
	
}
