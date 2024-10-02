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

import com.nakshtra.interior.customexcpetion.DesignNotFoundException;
import com.nakshtra.interior.dto.ErrorResponse;
import com.nakshtra.interior.entity.Design;
import com.nakshtra.interior.service.DesignService;




@RestController
@RequestMapping("/design")
public class DesignController {

		@Autowired
		private DesignService designService ;

		@PostMapping("/add")
		public ResponseEntity<?> registerdDesign(@RequestBody Design design) {
			Design createdDesign = designService.create(design);
			return new ResponseEntity<>(createdDesign, HttpStatus.CREATED);
		}

		@GetMapping("/all")
		public ResponseEntity<?> getAllDesignser() {
			try {
				return new ResponseEntity<>(designService.fetchAll(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>
				(new ErrorResponse("Design Fetching is failed", e.getMessage()),
						HttpStatus.BAD_REQUEST);
			}

		}
		@GetMapping("/getDesign/{DesignId}")
		public ResponseEntity<?> getDesignById(@PathVariable("DesignId") Integer id) {
			try {
				return  ResponseEntity.ok(designService.fetchById(id));
				
			} catch (Exception e) {
				
				ErrorResponse errorResponce=
			    new ErrorResponse("Design Fetching is failed", e.getMessage());
				return new ResponseEntity<>
				(errorResponce,HttpStatus.BAD_REQUEST);
			}
		}

		@PutMapping("/update/{DesignId}")
		public ResponseEntity<?> updateDesignById(@PathVariable("DesignId") Integer id,
				@RequestBody Design updateDesign) {
			try {
				Design existingDesign=designService.fetchById(id);
				
				return  ResponseEntity.ok(designService.update(updateDesign, existingDesign));
				
			} catch (Exception e) {
				
				ErrorResponse errorResponce= new ErrorResponse("Design updation is failed", e.getMessage());
				return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
			}
		}
		@DeleteMapping("/delete/{DesignId}")
		public ResponseEntity<?>deleteDesignById(@PathVariable ("DesignId") Integer id)
		{ try {
			Design existingDesign=designService.fetchById(id);
			return ResponseEntity.ok(designService.delete(existingDesign));
					
		} catch (Exception e) {

			ErrorResponse errorResponce= new ErrorResponse("Design deletion is failead", e.getMessage());
			return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
			
		}
			
		}
		}
		

