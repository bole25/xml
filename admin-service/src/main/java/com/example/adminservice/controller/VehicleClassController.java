package com.example.adminservice.controller;

import com.example.adminservice.model.FuelType;
import com.example.adminservice.model.VehicleClass;
import com.example.adminservice.service.FuelTypeService;
import com.example.adminservice.service.VehicleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/vehicleclass")
public class VehicleClassController {
    @Autowired
    VehicleClassService vehicleClassService;

    @PostMapping()
    public ResponseEntity<String> createVehicleClass(@RequestBody VehicleClass vc){
        return vehicleClassService.createVehicleClass(vc);
    }

    @DeleteMapping("/{name}")
    @Transactional
    public ResponseEntity<String> deleteVehicleClass(@PathVariable("name") String name){
        return vehicleClassService.deleteVehicleClass(name);
    }

    @GetMapping()
    public ResponseEntity<Set<VehicleClass>> getAll(){
        return vehicleClassService.getAll();
    }
}
