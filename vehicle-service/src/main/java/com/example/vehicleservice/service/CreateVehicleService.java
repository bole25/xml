package com.example.vehicleservice.service;

import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateVehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseEntity<String> createVehicle(Vehicle vehicle){
        try {
            vehicleRepository.save(vehicle);
            return new ResponseEntity<>("Vehicle created", HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
