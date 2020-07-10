package com.example.adminservice.controller;

import com.example.adminservice.model.FuelType;
import com.example.adminservice.model.Transmission;
import com.example.adminservice.service.FuelTypeService;
import com.example.adminservice.service.TransmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/transmission")
public class TransmissionController {

    @Autowired
    TransmissionService transmissionService;

    Logger logger = LoggerFactory.getLogger(TransmissionController.class);

    @PostMapping()
    public ResponseEntity<String> createTransmission(@RequestBody Transmission transmission, @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio kreiranje tipa mjenjaca {}. {}", username, transmission.getName(), LocalDateTime.now());
        return transmissionService.createTransmission(transmission, username);
    }

    @PostMapping("/{name}")
    public ResponseEntity<String> deleteTransmission(@PathVariable("name") String name,  @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio brisanje tipa mjenjaca {}. {}", username, name, LocalDateTime.now());
        return transmissionService.deleteTransmission(name, username);
    }

    @GetMapping()
    public ResponseEntity<Set<Transmission>> getAll(){
        return transmissionService.getAll();
    }
}
