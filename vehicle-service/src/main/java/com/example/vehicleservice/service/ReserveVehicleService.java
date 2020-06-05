package com.example.vehicleservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicleservice.dto.ReservationDTO;
import com.example.vehicleservice.model.Occupation;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;

@Service
public class ReserveVehicleService {

	@Autowired
	VehicleRepository vehicleRepo;
	
	 public ResponseEntity<String> reserveVehicle(ReservationDTO reservationDTO){
		 Vehicle vehicle = vehicleRepo.getOne(reservationDTO.getId());
		 if(vehicle == null) {
			 return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		 }
		 Occupation occupation = new Occupation(null, reservationDTO.getStart(), reservationDTO.getEnd());
		 vehicle.getOccupations().add(occupation);
		 vehicleRepo.save(vehicle);
		 return new ResponseEntity<>("Vehicle created", HttpStatus.OK);
	 }
}
