package com.nakshtra.interior.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nakshtra.interior.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	User findByUserName (String userName);
	@Query("SELECT u FROM User u WHERE LOWER(u.userName) = LOWER(:username)")
	User findByUserNameIgnoreCase(@Param("username") String username);

}