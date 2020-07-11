package com.example.gpsservice.controller;

import com.example.gpsservice.model.Vehicle;
import com.example.gpsservice.service.CoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coordinates")
public class GetCoordinates {

    @Autowired
    private CoordinatesService coordinatesService;

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleCoordinates(@PathVariable String id){
        Vehicle v;
        Long vehicle_id;
        try{
            vehicle_id = Long.parseLong(id);
        }catch (NumberFormatException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try{
            v= coordinatesService.getCoordinates(vehicle_id);
        }catch(Exception npe){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(v, HttpStatus.OK);
    }
}
