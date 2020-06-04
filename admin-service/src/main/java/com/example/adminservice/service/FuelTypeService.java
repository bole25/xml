package com.example.adminservice.service;

import com.example.adminservice.model.Brand;
import com.example.adminservice.model.FuelType;
import com.example.adminservice.repository.BrandRepository;
import com.example.adminservice.repository.FuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FuelTypeService {

    @Autowired
    FuelTypeRepository fuelTypeRepository;

    public ResponseEntity<String> createFuelType(FuelType fuelType){
        try{
            fuelTypeRepository.save(fuelType);
            return new ResponseEntity<>("FuelType created", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

    }

    @Transactional
    public ResponseEntity<String> deleteFuelType(String name){
        try {
            fuelTypeRepository.deleteByName(name);
            return new ResponseEntity<>("fueltype deleted", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

}
