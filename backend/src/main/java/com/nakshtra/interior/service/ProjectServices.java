package com.nakshtra.interior.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakshtra.interior.customexcpetion.ProjectNotFoundException;
import com.nakshtra.interior.dao.ProjectRepo;
import com.nakshtra.interior.dao.UserRepo;
import com.nakshtra.interior.entity.Project;
import com.nakshtra.interior.entity.User;

@Service
public class ProjectServices implements CurdService<Project, Integer> {

	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public Project create(Project project) {
		
		return projectRepo.save(project);
	}

	@Override
	public List<Project> fetchAll() {
	
		return projectRepo.findAll();
	}

	@Override
	public Project fetchById(Integer id) {
		
		return projectRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException("Invalid id"));
		
	}

	@Override
	public Project update(Project updateProject, Project existingProject) {
		
		existingProject.setId(updateProject.getId());
	    existingProject.setName(updateProject.getName());
	    existingProject.setStatus(updateProject.getStatus());
	    
		return projectRepo.save(existingProject);
	}

	@Override
	public String delete(Project project) {
		return project.getId()+" deleted";
	}

	public Project fetchProjectByClientId(User id) {
		
		return projectRepo.findByClient(id);
	}
}
