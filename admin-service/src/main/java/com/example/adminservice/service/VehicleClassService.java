package com.example.adminservice.service;

import com.example.adminservice.model.Brand;
import com.example.adminservice.model.VehicleClass;
import com.example.adminservice.repository.BrandRepository;
import com.example.adminservice.repository.VehicleClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleClassService {

    @Autowired
    VehicleClassRepository vehicleClassRepository;

    public ResponseEntity<String> createVehicleClass(VehicleClass v){
        try{
            vehicleClassRepository.save(v);
            return new ResponseEntity<>("VehicleClass created", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

    }
}
