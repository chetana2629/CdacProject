package com.nakshtra.interior.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nakshtra.interior.entity.Furniture;

public interface FurnitureRepo extends JpaRepository<Furniture, Integer> {

	Furniture findByName(String name);
}
