package com.example.vehicleservice.controller;

import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.service.CreateVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class CreateVehicleController {
    @Autowired
    CreateVehicleService createVehicleService;

    @PostMapping("/create")
    public ResponseEntity<String> createVehicle(@RequestBody Vehicle vehicle){
        return createVehicleService.createVehicle(vehicle);
    }
}
