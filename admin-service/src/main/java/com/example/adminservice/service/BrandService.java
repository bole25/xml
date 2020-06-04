package com.example.adminservice.service;

import com.example.adminservice.model.Brand;
import com.example.adminservice.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public ResponseEntity<String> createBrand(Brand brand){
        try{
            brandRepository.save(brand);
            return new ResponseEntity<>("Brand created", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

    }
}
