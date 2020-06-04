package com.example.adminservice.controller;

import com.example.adminservice.model.FuelType;
import com.example.adminservice.model.VehicleClass;
import com.example.adminservice.service.FuelTypeService;
import com.example.adminservice.service.VehicleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicleclass")
public class VehicleClassController {
    @Autowired
    VehicleClassService vehicleClassService;

    @PostMapping("/create")
    public ResponseEntity<String> createVehicleClass(@RequestBody VehicleClass vc){
        return vehicleClassService.createVehicleClass(vc);
    }
}
