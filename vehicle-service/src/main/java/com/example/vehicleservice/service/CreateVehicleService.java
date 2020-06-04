package com.example.vehicleservice.service;

import com.example.vehicleservice.dto.VehicleDTO;
import com.example.vehicleservice.model.Occupation;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CreateVehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseEntity<String> createVehicle(VehicleDTO vehicleDTO){
        try {
            Vehicle v = new Vehicle(vehicleDTO);
            vehicleRepository.save(v);
            return new ResponseEntity<>("Vehicle created", HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
