package com.nakshtra.interior.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakshtra.interior.customexcpetion.DesignNotFoundException;
import com.nakshtra.interior.dao.DesignRepo;
import com.nakshtra.interior.entity.Design;

@Service
public class DesignService implements CurdService<Design, Integer> {

	@Autowired
	private DesignRepo designRepo;
	
	@Override
	public Design create(Design design) {
		
		return designRepo.save(design);
	}

	@Override
	public List<Design> fetchAll() {
		
		return designRepo.findAll();
	}

	@Override
	public Design fetchById(Integer id) {
		
		return designRepo.findById(id).orElseThrow (()-> new DesignNotFoundException("Invalid Disgn ID"));
	}

	@Override
	public Design update(Design updateDesign, Design existingDesign) {
		existingDesign.setDescription(updateDesign.getDescription());
		existingDesign.setName(updateDesign.getName());
		//existingDesign.setRooms(updateDesign.getRooms());
		existingDesign.setImage(updateDesign.getImage());
		
		return designRepo.save(existingDesign);
	}

	@Override
	public String delete(Design design) {
		designRepo.delete(design);
		return design.getId()+" deleted";
	}

	
	
}
