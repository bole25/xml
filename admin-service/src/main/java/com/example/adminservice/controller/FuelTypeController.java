package com.example.adminservice.controller;

import com.example.adminservice.model.FuelType;
import com.example.adminservice.service.FuelTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/fueltype")
public class FuelTypeController {

    @Autowired
    FuelTypeService fuelTypeService;

    Logger logger = LoggerFactory.getLogger(FuelTypeController.class);

    @PostMapping()
    public ResponseEntity<String> createFuelType(@RequestBody FuelType fuelType, @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio kreiranje tipa goriva {}. {}", username, fuelType.getName(), LocalDateTime.now());
        return fuelTypeService.createFuelType(fuelType, username);
    }

    @PostMapping("/{name}")
    public ResponseEntity<String> deleteFuelType(@PathVariable("name") String name,  @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio brisanje tipa goriva {}. {}", username, name, LocalDateTime.now());
        return fuelTypeService.deleteFuelType(name, username);
    }

    @GetMapping()
    public ResponseEntity<Set<FuelType>> getAll(){
        return fuelTypeService.getAll();
    }
}
