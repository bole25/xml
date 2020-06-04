package com.example.adminservice.controller;

import com.example.adminservice.model.FuelType;
import com.example.adminservice.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fueltype")
public class FuelTypeController {

    @Autowired
    FuelTypeService fuelTypeService;

    @PostMapping("/create")
    public ResponseEntity<String> createFuelType(@RequestBody FuelType fuelType){
        return fuelTypeService.createFuelType(fuelType);
    }
}
