package com.example.adminservice.controller;

import com.example.adminservice.model.FuelType;
import com.example.adminservice.model.VehicleClass;
import com.example.adminservice.service.FuelTypeService;
import com.example.adminservice.service.VehicleClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/vehicleclass")
public class VehicleClassController {
    @Autowired
    VehicleClassService vehicleClassService;

    Logger logger = LoggerFactory.getLogger(VehicleClassController.class);

    @PostMapping()
    public ResponseEntity<String> createVehicleClass(@RequestBody VehicleClass vc, @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio kreiranje klase vozila {}. {}", username, vc.getName(), LocalDateTime.now());
        return vehicleClassService.createVehicleClass(vc, username);
    }

    @PostMapping("/{name}")
    public ResponseEntity<String> deleteVehicleClass(@PathVariable("name") String name,  @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio brisanje klase vozila {}. {}", username, name, LocalDateTime.now());
        return vehicleClassService.deleteVehicleClass(name, username);
    }

    @GetMapping()
    public ResponseEntity<Set<VehicleClass>> getAll(){
        return vehicleClassService.getAll();
    }
}
