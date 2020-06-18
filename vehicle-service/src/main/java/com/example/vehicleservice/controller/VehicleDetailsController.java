package com.example.vehicleservice.controller;

import com.example.vehicleservice.dto.VehicleDTO;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.service.VehicleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/details")
public class VehicleDetailsController {

    @Autowired
    VehicleDetailsService vehicleDetailsService;

    @GetMapping("/{id1}")
    public ResponseEntity<VehicleDTO> getVehicleDetails(@PathVariable("id1") String id1){
        try{
            return vehicleDetailsService.getVehicleDetails(Long.parseLong(id1));
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
