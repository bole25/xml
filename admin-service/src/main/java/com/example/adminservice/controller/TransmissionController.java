package com.example.adminservice.controller;

import com.example.adminservice.model.FuelType;
import com.example.adminservice.model.Transmission;
import com.example.adminservice.service.FuelTypeService;
import com.example.adminservice.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transmission")
public class TransmissionController {

    @Autowired
    TransmissionService transmissionService;

    @PostMapping("/create")
    public ResponseEntity<String> createTransmission(@RequestBody Transmission transmission){
        return transmissionService.createTransmission(transmission);
    }
}
