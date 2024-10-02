package com.nakshtra.interior.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nakshtra.interior.entity.Project;
import com.nakshtra.interior.entity.User;


public interface ProjectRepo extends JpaRepository<Project ,Integer> {

	
	Project findByClient (User client);
}
