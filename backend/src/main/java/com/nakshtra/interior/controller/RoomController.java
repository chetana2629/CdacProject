package com.nakshtra.interior.controller;

import java.util.List;

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

import com.nakshtra.interior.customexcpetion.UserNotFoundException;
import com.nakshtra.interior.dto.ErrorResponse;
import com.nakshtra.interior.entity.Rooms;
import com.nakshtra.interior.entity.User;
import com.nakshtra.interior.service.RoomServices;
import com.nakshtra.interior.service.UserService;



@RestController
@RequestMapping("/room")
public class RoomController {

		@Autowired
		private RoomServices roomService ;

		@PostMapping("/add")
		public ResponseEntity<?> registerdUser(@RequestBody Rooms rooms) {
			Rooms createdRooms = roomService.create(rooms);
			return new ResponseEntity<>(createdRooms, HttpStatus.CREATED);
		}

		@GetMapping("/all")
	    public ResponseEntity<?> getAllRooms() {
	        try {
	            List<Rooms> rooms = roomService.fetchAll();
	            return new ResponseEntity<>(rooms, HttpStatus.OK);
	        } catch (Exception e) {
	            ErrorResponse errorResponse = new ErrorResponse("Fetching rooms data failed", e.getMessage());
	            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	        }
	    }
		@GetMapping("/getroom/{roomId}")
		public ResponseEntity<?> getUserById(@PathVariable("roomId") Integer id) {
			try {
				return  ResponseEntity.ok(roomService.fetchById(id));
				
			} catch (Exception e) {
				
				ErrorResponse errorResponce=
			    new ErrorResponse("Rooms Fetching data is failed", e.getMessage());
				return new ResponseEntity<>
				(errorResponce,HttpStatus.BAD_REQUEST);
			}
		}

		@PutMapping("/update/{roomId}")
		public ResponseEntity<?> updateuserById(@PathVariable("roomId") Integer id,
				@RequestBody Rooms updateRooms) {
			try {
				Rooms existingRooms=roomService.fetchById(id);
				
				return  ResponseEntity.ok(roomService.update(updateRooms, existingRooms));
				
			} catch (Exception e) {
				
				ErrorResponse errorResponce= new ErrorResponse("Room updation is failed", e.getMessage());
				return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
			}
		}
		@DeleteMapping("/delete/{roomId}")
		public ResponseEntity<?>deleteUserById(@PathVariable ("roomId") Integer id)
		{ try {
			Rooms existingRooms=roomService.fetchById(id);
			return ResponseEntity.ok(roomService.delete(existingRooms));
					
		} catch (Exception e) {

			ErrorResponse errorResponce= new ErrorResponse("Rooms deletion is failead", e.getMessage());
			return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
			
		}
			
		}
		
		@GetMapping("/getbyName/{name}")
		public ResponseEntity<?> getRoomByRoomName(@PathVariable String name){
			try {
				ResponseEntity res = null;
				List<Rooms> foundRoom = roomService.findByName(name);
				if(foundRoom != null) {
					return res.ok(foundRoom);
				
				}else {
					throw new UserNotFoundException("Invalid Username..");
				}
			
			} catch (Exception e) {
				return new ResponseEntity<>(new ErrorResponse("fetching user by username failed.", e.getMessage()),HttpStatus.BAD_REQUEST);
			}
			
		
		}
}
