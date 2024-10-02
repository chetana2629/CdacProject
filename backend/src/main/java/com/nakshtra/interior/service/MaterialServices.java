package com.nakshtra.interior.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakshtra.interior.customexcpetion.MaterialNotFoundException;
import com.nakshtra.interior.dao.MaterialRepo;
import com.nakshtra.interior.dao.UserRepo;
import com.nakshtra.interior.entity.Material;

@Service
public class MaterialServices implements CurdService<Material, Integer> {

	@Autowired
	private MaterialRepo  materialRepo;

	@Override
	public Material create(Material material) {
		
		return materialRepo.save(material);
	}

	@Override
	public List<Material> fetchAll() {
		
		return materialRepo.findAll();
	}

	@Override
	public Material fetchById(Integer id) {
		
		return materialRepo.findById(id).orElseThrow(()-> new MaterialNotFoundException("Invalid id"));
	}

	@Override
	public Material update(Material updateMaterial, Material existingMaterial) {
		existingMaterial.setDesign(updateMaterial.getDesign());
		existingMaterial.setName(updateMaterial.getName());
		existingMaterial.setPrice(updateMaterial.getPrice());
		return materialRepo.save(existingMaterial);
	}

	@Override
	public String delete(Material material) {
		materialRepo.delete(material);
		return material.getId()+ " Deleted";
	}
	
	public Material fetchByName(String name) {
		
		return materialRepo.findByName(name);
	}

}
