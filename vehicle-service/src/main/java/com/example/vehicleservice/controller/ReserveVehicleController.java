package com.example.vehicleservice.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vehicleservice.dto.ReservationDTO;
import com.example.vehicleservice.service.ReserveVehicleService;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("vehicle/reserve")
public class ReserveVehicleController {
	
	@Autowired
	private ReserveVehicleService reserveService;

	// Fizicka rezervacije, kada agent vozila hoce da zauzme svoje vozilo
	@PostMapping()
	public ResponseEntity<String> reserveVehicle(@RequestBody ReservationDTO reservation, @RequestHeader("Username") String username){
		reserveService.physicalReservation(reservation,username);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@PostMapping("/requestService")
	public ResponseEntity<?> reserveVehicleFromRequestService(@RequestBody String encodedRequests){
		Gson gson = new Gson();
		Type type = new TypeToken<ReservationDTO>(){}.getType();
		ReservationDTO reservation = gson.fromJson(encodedRequests,type);
		reserveService.reserveVehicle(reservation);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
