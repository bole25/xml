package com.example.adminservice.controller;

import com.example.adminservice.model.FuelType;
import com.example.adminservice.model.Transmission;
import com.example.adminservice.service.FuelTypeService;
import com.example.adminservice.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/transmission")
public class TransmissionController {

    @Autowired
    TransmissionService transmissionService;

    @PostMapping()
    public ResponseEntity<String> createTransmission(@RequestBody Transmission transmission){
        return transmissionService.createTransmission(transmission);
    }

    @PostMapping("/{name}")
    @Transactional
    public ResponseEntity<String> deleteTransmission(@PathVariable("name") String name){
        return transmissionService.deleteTransmission(name);
    }

    @GetMapping()
    public ResponseEntity<Set<Transmission>> getAll(){
        return transmissionService.getAll();
    }
}
