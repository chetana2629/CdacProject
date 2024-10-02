package com.nakshtra.interior.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nakshtra.interior.entity.Rooms;

public interface RoomRepo  extends JpaRepository<Rooms, Integer>{
	 List<Rooms> findByName(String name);
}
