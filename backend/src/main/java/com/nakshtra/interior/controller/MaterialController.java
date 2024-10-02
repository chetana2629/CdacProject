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

import com.nakshtra.interior.customexcpetion.MaterialNotFoundException;
import com.nakshtra.interior.dto.ErrorResponse;
import com.nakshtra.interior.entity.Material;
import com.nakshtra.interior.service.MaterialServices;


@RestController
@RequestMapping("/material")
public class MaterialController {
	@Autowired
	private MaterialServices materialService ;

	@PostMapping("/add")
	public ResponseEntity<?> registerdMaterial(@RequestBody Material material) {
		Material createdMaterial = materialService.create(material);
		return new ResponseEntity<>(createdMaterial, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllMaterialser() {
		try {
			return new ResponseEntity<>(materialService.fetchAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>
			(new ErrorResponse("Material Fetching is failed", e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	@GetMapping("/getMaterial/{MaterialId}")
	public ResponseEntity<?> getMaterialById(@PathVariable("MaterialId") Integer id) {
		try {
			return  ResponseEntity.ok(materialService.fetchById(id));
			
		} catch (Exception e) {
			
			ErrorResponse errorResponce=
		    new ErrorResponse("Material Fetching is failed", e.getMessage());
			return new ResponseEntity<>
			(errorResponce,HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{MaterialId}")
	public ResponseEntity<?> updateMaterialById(@PathVariable("MaterialId") Integer id,
			@RequestBody Material updateMaterial) {
		try {
			Material existingMaterial=materialService.fetchById(id);
			
			return  ResponseEntity.ok(materialService.update(updateMaterial, existingMaterial));
			
		} catch (Exception e) {
			
			ErrorResponse errorResponce= new ErrorResponse("Material updation is failed", e.getMessage());
			return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/delete/{MaterialId}")
	public ResponseEntity<?>deleteMaterialById(@PathVariable ("MaterialId") Integer id)
	{ try {
		Material existingMaterial=materialService.fetchById(id);
		return ResponseEntity.ok(materialService.delete(existingMaterial));
				
	} catch (Exception e) {

		ErrorResponse errorResponce= new ErrorResponse("Material deletion is failead", e.getMessage());
		return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
		
	}
		
	}
	@GetMapping("/getbyName/{name}")
	public ResponseEntity<?> getMaterialByMaterialName(@PathVariable String name){
		try {
			ResponseEntity res = null;
			Material foundMaterial = materialService.fetchByName(name);
			if(foundMaterial != null) {
				return res.ok(foundMaterial);
			
			}else {
				throw new MaterialNotFoundException("Invalid Materialname..");
			}
		
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse("fetching Material by Materialname failed.", e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
