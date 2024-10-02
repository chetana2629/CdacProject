package com.nakshtra.interior.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakshtra.interior.customexcpetion.FurnitureNotFoundException;
import com.nakshtra.interior.dao.FurnitureRepo;
import com.nakshtra.interior.entity.Furniture;

@Service

public class FurnitureService implements CurdService<Furniture, Integer> {

	@Autowired
	private FurnitureRepo furnitureRepo;
	@Override
	public Furniture create(Furniture furniture) {
				return furnitureRepo.save(furniture);
	}

	@Override
	public List<Furniture> fetchAll() {
		
		return furnitureRepo.findAll();
	}

	@Override
	public Furniture fetchById(Integer id) {
		
		return furnitureRepo.findById(id).orElseThrow(() -> new FurnitureNotFoundException("Invalid ID"));
	}

	@Override
	public Furniture update(Furniture updateFurniture, Furniture existingFurniture) {
		existingFurniture.setDescription(updateFurniture.getDescription());
		existingFurniture.setName(updateFurniture.getName());
		existingFurniture.setPrice(updateFurniture.getPrice());
		
		return furnitureRepo.save(existingFurniture);
	}

	@Override
	public String delete(Furniture furniture) {
		furnitureRepo.delete(furniture);
		 return  furniture.getId() +" deleted";
		
		
	}

	public Furniture fetchByFurnitureName(String name) {
		
		return furnitureRepo.findByName(name);
	}
}
