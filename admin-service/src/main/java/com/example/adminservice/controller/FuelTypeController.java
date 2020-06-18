package com.example.adminservice.controller;

import com.example.adminservice.model.FuelType;
import com.example.adminservice.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/fueltype")
public class FuelTypeController {

    @Autowired
    FuelTypeService fuelTypeService;

    @PostMapping()
    public ResponseEntity<String> createFuelType(@RequestBody FuelType fuelType){
        return fuelTypeService.createFuelType(fuelType);
    }

    @PostMapping("/{name}")
    @Transactional
    public ResponseEntity<String> deleteFuelType(@PathVariable("name") String name){
        return fuelTypeService.deleteFuelType(name);
    }

    @GetMapping()
    public ResponseEntity<Set<FuelType>> getAll(){
        return fuelTypeService.getAll();
    }
}
