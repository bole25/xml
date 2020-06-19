package com.example.vehicleservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicleservice.dto.ReservationDTO;
import com.example.vehicleservice.model.Occupation;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ReserveVehicleService {

	@Autowired
	VehicleRepository vehicleRepo;

	@Transactional
	@Modifying
	 public ResponseEntity<String> reserveVehicle(ReservationDTO reservationDTO){
		 Vehicle vehicle = vehicleRepo.getVehicle(reservationDTO.getId());

		 if(vehicle == null) {
			 return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		 }

		if(reservationDTO.getStart().after(reservationDTO.getEnd())){
			return new ResponseEntity<>("EndDate is before start", HttpStatus.BAD_REQUEST);
		}

		if(!available(reservationDTO.getStart(), reservationDTO.getEnd(), vehicle.getStartDate(), vehicle.getEndDate())){
			return new ResponseEntity<>("Car is not available in that period", HttpStatus.BAD_REQUEST);
		}


		Boolean work = Boolean.TRUE;
		for(Occupation o: vehicle.getOccupations()){
			if(!this.notInOccupation(reservationDTO.getStart(), reservationDTO.getEnd(), o.getStartDate(), o.getEndDate())){
				work = Boolean.FALSE;
				break;
			}
		}

		if(!work){
			return new ResponseEntity<>("Reserved in that period", HttpStatus.BAD_REQUEST);
		}

		 Occupation occupation = new Occupation(null, reservationDTO.getStart(), reservationDTO.getEnd());
		 vehicle.getOccupations().add(occupation);
		 vehicleRepo.save(vehicle);
		 return new ResponseEntity<>("Car successfully reserved", HttpStatus.OK);
	 }

	 private Boolean notInOccupation(Date start, Date end, Date occStart, Date occEnd){
		 if(start.after(occEnd)) {
			 return Boolean.TRUE;
		 }
		 if(end.before(occStart)){
		 	return Boolean.TRUE;
		 }

		 return Boolean.FALSE;
	 }

	 private Boolean available(Date start, Date end, Date carStart, Date carEnd){
		if(!start.before(carStart) && !start.after(carEnd) && !end.before(carStart) && !end.after(carEnd)){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	 }
}
