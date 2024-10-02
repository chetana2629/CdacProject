package com.nakshtra.interior.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakshtra.interior.dao.RoomRepo;
import com.nakshtra.interior.entity.Rooms;

@Service
public class RoomServices implements CurdService<Rooms, Integer> {

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public Rooms create(Rooms room) {
        return roomRepo.save(room);
    }

    @Override
    public List<Rooms> fetchAll() {
        return roomRepo.findAll();
    }

    @Override
    public Rooms fetchById(Integer id) {
        Optional<Rooms> optionalRoom = roomRepo.findById(id);
        return optionalRoom.orElse(null);
    }

    @Override
    public Rooms update(Rooms existingRoom , Rooms newRoom) {
    	existingRoom.setId(newRoom.getId());
    	existingRoom.setName(newRoom.getName());
    	existingRoom.setProject(newRoom.getProject());
    	existingRoom.setType(newRoom.getType());
    	
    	return roomRepo.save(existingRoom);
    }
    

    @Override
    public String delete(Rooms room) {
        if (roomRepo.existsById(room.getId())) {
            roomRepo.delete(room);
            return "Room deleted successfully";
        }
        return "Room not found";
    }

  
    public List<Rooms> findByName(String name) {
        return roomRepo.findByName(name);
    }

}
