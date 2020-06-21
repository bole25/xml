package com.example.vehicleservice.controller;

import java.util.Set;

import com.example.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicleservice.dto.ReservationDTO;
import com.example.vehicleservice.dto.SearchByCompanyUsernameDTO;
import com.example.vehicleservice.dto.ShowVehicleDTO;
import com.example.vehicleservice.service.ReserveVehicleService;

@RestController
@RequestMapping("vehicle/reserve")
public class ReserveVehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ReserveVehicleService reserveService;

	@PostMapping("/GetVehiclesByDateAndUsername")
	public ResponseEntity<Set<ShowVehicleDTO>> GetVehiclesByCompanyUsername(@RequestBody SearchByCompanyUsernameDTO search){
		return vehicleService.GetVehiclesByDateAndCompanyUsername(search);
	}
	@PostMapping("/reserveVehicle")
	public ResponseEntity<String> reserveVehicle(@RequestBody ReservationDTO search){
		return reserveService.reserveVehicle(search);
	}
	
}
