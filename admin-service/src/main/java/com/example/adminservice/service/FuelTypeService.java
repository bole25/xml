package com.example.adminservice.service;

import com.example.adminservice.model.Brand;
import com.example.adminservice.model.FuelType;
import com.example.adminservice.repository.BrandRepository;
import com.example.adminservice.repository.FuelTypeRepository;
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
public class FuelTypeService {

    @Autowired
    FuelTypeRepository fuelTypeRepository;

    Logger logger = LoggerFactory.getLogger(FuelTypeService.class);


    public ResponseEntity<String> createFuelType(FuelType fuelType, String username){
        try{
            fuelTypeRepository.save(fuelType);
            logger.info("Admin {} je kreirao tip goriva {}. {}", username, fuelType.getName(), LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Neuspjesno kreiranje tipa goriva {} od strane admina {}. {}", fuelType.getName(),username, LocalDateTime.now());
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

    }

    @Transactional
    public ResponseEntity<String> deleteFuelType(String name,String username){
        try {
            fuelTypeRepository.deleteByName(name);
            logger.info("Admin {} je obrisao tip goriva {}. {}", username, name, LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Neuspjesno brisanje tipa goriva {} od strane admina {}. {}", name, username, LocalDateTime.now());
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Set<FuelType>> getAll(){
        try {
            Set<FuelType> ret = fuelTypeRepository.getAll();
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
