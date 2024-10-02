package com.nakshtra.interior.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakshtra.interior.customexcpetion.ProjectNotFoundException;
import com.nakshtra.interior.dto.ErrorResponse;
import com.nakshtra.interior.entity.Project;
import com.nakshtra.interior.entity.User;
import com.nakshtra.interior.service.ProjectServices;




@RestController
@RequestMapping("/project")
public class ProjectController {

		@Autowired
		private ProjectServices projectServices;

		@PostMapping("/add")
		public ResponseEntity<?> registerdUser(@RequestBody Project project) {
			Project createdProject = projectServices.create(project);
			return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
		}

		@GetMapping("/all")
		public ResponseEntity<?> getAllUserser() {
			try {
				return new ResponseEntity<>(projectServices.fetchAll(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>
				(new ErrorResponse("User Fetching is failed", e.getMessage()),
						HttpStatus.BAD_REQUEST);
			}

		}
		@GetMapping("/getproject/{projectId}")
		public ResponseEntity<?> getUserById(@PathVariable("projectId") Integer id) {
			try {
				return  ResponseEntity.ok(projectServices.fetchById(id));
				
			} catch (Exception e) {
				
				ErrorResponse errorResponce=
			    new ErrorResponse("Project Fetching is failed", e.getMessage());
				return new ResponseEntity<>
				(errorResponce,HttpStatus.BAD_REQUEST);
			}
		}

		@PutMapping("/update/{projectId}")
		public ResponseEntity<?> updateuserById(@PathVariable("projectId") Integer id,
				@RequestBody Project updateProject) {
			try {
				Project existingProject=projectServices.fetchById(id);
				
				return  ResponseEntity.ok(projectServices.update(updateProject, existingProject));
				
			} catch (Exception e) {
				
				ErrorResponse errorResponce= new ErrorResponse("Project updation is failed", e.getMessage());
				return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
			}
		}
		@DeleteMapping("/delete/{projectId}")
		public ResponseEntity<?>deleteUserById(@PathVariable ("projectId") Integer id)
		{ try {
			Project existingProject=projectServices.fetchById(id);
			return ResponseEntity.ok(projectServices.delete(existingProject));
					
		} catch (Exception e) {

			ErrorResponse errorResponce= new ErrorResponse("User deletion is failead", e.getMessage());
			return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
			
		}
			
		}
		@GetMapping("/getbyName/{name}")
		public ResponseEntity<?> getUserByProjectName(@PathVariable User id){
			try {
				ResponseEntity res = null;
				Project foundProject = projectServices.fetchProjectByClientId(id);
				if(foundProject != null) {
					return res.ok(foundProject);
				
				}else {
					throw new ProjectNotFoundException("Invalid Client Id..");
				}
			
			} catch (Exception e) {
				return new ResponseEntity<>(new ErrorResponse("fetching Project by clientId failed.", e.getMessage()),HttpStatus.BAD_REQUEST);
			}
			
			
		}
		
}
