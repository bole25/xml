package com.example.vehicleservice.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicleservice.dto.SearchByCompanyUsernameDTO;
import com.example.vehicleservice.dto.ShowVehicleDTO;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;

@Service
public class ShowVehiclesService {

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseEntity<Set<ShowVehicleDTO>> showVehicles(){
        Set<Vehicle> vehicleSet = new HashSet<>();
        Set<ShowVehicleDTO> showVehicleDTOS = new HashSet<>();
        try {
            vehicleSet = vehicleRepository.showVehicles();
            for (Vehicle v : vehicleSet) {
                ShowVehicleDTO showVehicleDTO = new ShowVehicleDTO(v);
                showVehicleDTOS.add(showVehicleDTO);
            }
            return new ResponseEntity<>(showVehicleDTOS,HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
	public ResponseEntity<Set<ShowVehicleDTO>> GetVehiclesByDateAndCompanyUsername(SearchByCompanyUsernameDTO search) {

		Set<ShowVehicleDTO> vehiclesDTO = new HashSet<ShowVehicleDTO>();
		Set<Vehicle> vehicles = vehicleRepository.searchVehicle(search.getFrom(), search.getTo());
		for(Vehicle v : vehicles) {
			if(v.getCompanyUsername().equals(search.getUsername())) {
				vehiclesDTO.add(new ShowVehicleDTO(v));
			}
		}
		return new ResponseEntity<>(vehiclesDTO,HttpStatus.OK);
	}
}
