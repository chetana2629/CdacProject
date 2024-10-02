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

import com.nakshtra.interior.customexcpetion.FurnitureNotFoundException;
import com.nakshtra.interior.dto.ErrorResponse;
import com.nakshtra.interior.entity.Furniture;
import com.nakshtra.interior.service.FurnitureService;
import com.nakshtra.interior.service.FurnitureService;


@RestController
@RequestMapping("/furniture")
public class FurnitureController {
	@Autowired
	private FurnitureService FurnitureService ;

	@PostMapping("/add")
	public ResponseEntity<?> registerdFurniture(@RequestBody Furniture Furniture) {
		Furniture createdFurniture = FurnitureService.create(Furniture);
		return new ResponseEntity<>(createdFurniture, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllFurnitureser() {
		try {
			return new ResponseEntity<>(FurnitureService.fetchAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>
			(new ErrorResponse("Furniture Fetching is failed", e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	@GetMapping("/getFurniture/{FurnitureId}")
	public ResponseEntity<?> getFurnitureById(@PathVariable("FurnitureId") Integer id) {
		try {
			return  ResponseEntity.ok(FurnitureService.fetchById(id));
			
		} catch (Exception e) {
			
			ErrorResponse errorResponce=
		    new ErrorResponse("Furniture Fetching is failed", e.getMessage());
			return new ResponseEntity<>
			(errorResponce,HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{FurnitureId}")
	public ResponseEntity<?> updateFurnitureById(@PathVariable("FurnitureId") Integer id,
			@RequestBody Furniture updateFurniture) {
		try {
			Furniture existingFurniture=FurnitureService.fetchById(id);
			
			return  ResponseEntity.ok(FurnitureService.update(updateFurniture, existingFurniture));
			
		} catch (Exception e) {
			
			ErrorResponse errorResponce= new ErrorResponse("Furniture updation is failed", e.getMessage());
			return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/delete/{FurnitureId}")
	public ResponseEntity<?>deleteFurnitureById(@PathVariable ("FurnitureId") Integer id)
	{ try {
		Furniture existingFurniture=FurnitureService.fetchById(id);
		return ResponseEntity.ok(FurnitureService.delete(existingFurniture));
				
	} catch (Exception e) {

		ErrorResponse errorResponce= new ErrorResponse("Furniture deletion is failead", e.getMessage());
		return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
		
	}
		
	}
	@GetMapping("/getbyName/{name}")
	public ResponseEntity<?> getFurnitureByFurnitureName(@PathVariable String name){
		try {
			ResponseEntity res = null;
			Furniture foundFurniture = FurnitureService.fetchByFurnitureName(name);
			if(foundFurniture != null) {
				return res.ok(foundFurniture);
			
			}else {
				throw new FurnitureNotFoundException("Invalid Furniturename..");
			}
		
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse("fetching Furniture by Furniturename failed.", e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
