package com.example.vehicleservice.controller;

import com.example.vehicleservice.dto.SearchDTO;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.service.SearchVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/search")
public class SearchVehicleController {

    @Autowired
    SearchVehicleService searchVehicleService;

    @PostMapping()
    public ResponseEntity<Set<Vehicle>> searchVehicles(@RequestBody SearchDTO searchDTO){
        System.out.println(searchDTO);
        return searchVehicleService.searchVehicle(searchDTO);
    }
}
