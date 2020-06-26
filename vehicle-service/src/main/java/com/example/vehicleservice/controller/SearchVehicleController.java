package com.example.vehicleservice.controller;

import com.example.vehicleservice.dto.SearchDTO;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.service.SearchVehicleService;
import javafx.util.converter.LocalDateStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/search")
public class SearchVehicleController {

    @Autowired
    SearchVehicleService searchVehicleService;

    Logger logger = LoggerFactory.getLogger(SearchVehicleController.class);

    @PostMapping()
    public ResponseEntity<Set<Vehicle>> searchVehicles(@RequestBody SearchDTO searchDTO){
        logger.warn("Zatrazena pretraga za grad {}. {}", searchDTO.getPlace(), LocalDateTime.now());
        System.out.println(searchDTO);
        return searchVehicleService.searchVehicle(searchDTO);
    }
}
