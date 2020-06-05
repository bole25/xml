package com.example.vehicleservice.controller;

import com.example.vehicleservice.service.GetInfoFromAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/formDetails")
public class FormInformationController {

    @Autowired
    private GetInfoFromAdminService infoService;

    @GetMapping("/brands")
    public ResponseEntity<String> getBrands(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(infoService.getData("/brand/getall"), headers, HttpStatus.OK);
    }

    @GetMapping("/transmissions")
    public ResponseEntity<String> getTransmissions(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(infoService.getData("/transmission/getall"), headers, HttpStatus.OK);
    }

    @GetMapping("/vehicle_classes")
    public ResponseEntity<String> getVehicleClass(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(infoService.getData("/vehicleclass/getall"), headers, HttpStatus.OK);
    }

    @GetMapping("/fuel_type")
    public ResponseEntity<String> getFuelType(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(infoService.getData("/fueltype/getall"), headers, HttpStatus.OK);
    }
}
