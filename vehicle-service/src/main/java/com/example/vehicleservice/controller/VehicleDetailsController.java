package com.example.vehicleservice.controller;

import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.service.VehicleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleDetailsController {

    @Autowired
    VehicleDetailsService vehicleDetailsService;

    @GetMapping("/vehicleDetails/{id1}")
    public ResponseEntity<Vehicle> getVehicleDetails(@PathVariable("id1") String id1){
        try{
            return vehicleDetailsService.getVehicleDetails(Long.parseLong(id1));
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
