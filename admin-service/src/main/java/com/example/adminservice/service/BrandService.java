package com.example.adminservice.service;

import com.example.adminservice.controller.BrandController;
import com.example.adminservice.model.Brand;
import com.example.adminservice.repository.BrandRepository;
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
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    Logger logger = LoggerFactory.getLogger(BrandService.class);

    public ResponseEntity<String> createBrand(Brand brand, String username){
        try{
            brandRepository.save(brand);
            logger.info("Admin {} je kreirao brend {}. {}", username, brand.getName(), LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Neuspjesno kreiranje branda {} od strane admina {}. {}", brand.getName(),username, LocalDateTime.now());
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

    }

    @Transactional
    public ResponseEntity<String> deleteBrand(String name, String username){
       try {
           Brand b = brandRepository.findBrand(name);
           brandRepository.delete(b);
           logger.info("Admin {} je obrisao brend {}. {}", username, name, LocalDateTime.now());
           return new ResponseEntity<>("", HttpStatus.OK);
       }
       catch (Exception e){
           e.printStackTrace();
           logger.error("Neuspjesno brisanje branda {} od strane admina {}. {}", name,username, LocalDateTime.now());
           return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
       }
    }

    @Transactional
    public ResponseEntity<Set<Brand>> getAll(){
        try {
            Set<Brand> ret = brandRepository.getAll();
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
