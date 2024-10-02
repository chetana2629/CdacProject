package com.nakshtra.interior.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nakshtra.interior.entity.Material;

public interface MaterialRepo extends JpaRepository<Material, Integer> {

	Material findByName(String name);
}
