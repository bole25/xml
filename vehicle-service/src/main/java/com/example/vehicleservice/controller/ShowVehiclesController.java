package com.example.vehicleservice.controller;

import com.example.vehicleservice.dto.ShowVehicleDTO;
import com.example.vehicleservice.service.ShowVehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ShowVehiclesController {

    @Autowired
    ShowVehiclesService showVehiclesService;

    @GetMapping("/showVehicles")
    public ResponseEntity<Set<ShowVehicleDTO>> showVehicles(){
        return showVehiclesService.showVehicles();
    }
}
