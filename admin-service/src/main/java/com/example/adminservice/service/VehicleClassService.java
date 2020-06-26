package com.example.adminservice.service;

import com.example.adminservice.controller.BrandController;
import com.example.adminservice.model.Brand;
import com.example.adminservice.model.Transmission;
import com.example.adminservice.model.VehicleClass;
import com.example.adminservice.repository.BrandRepository;
import com.example.adminservice.repository.VehicleClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class VehicleClassService {

    @Autowired
    VehicleClassRepository vehicleClassRepository;

    Logger logger = LoggerFactory.getLogger(VehicleClassService.class);

    public ResponseEntity<String> createVehicleClass(VehicleClass v, String username){
        try{
            vehicleClassRepository.save(v);
            logger.info("Admin {} je kreirao klasu vozila {} za brend {}. {}", username, v.getName() , LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Neuspjesno kreiranje klase vozila {} od strane admina {}. {}",v.getName(), username, LocalDateTime.now());
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

    }

    @Transactional
    public ResponseEntity<String> deleteVehicleClass(String name, String username){
        try {
            vehicleClassRepository.deleteByName(name);
            logger.info("Admin {} je obrisao klasu vozila {}. {}", username, name, LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Neuspjesno brisanje klase vozila {} od strane admina {}. {}", name, username, LocalDateTime.now());
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Set<VehicleClass>> getAll() {
        try {
            Set<VehicleClass> ret = vehicleClassRepository.getAll();
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
