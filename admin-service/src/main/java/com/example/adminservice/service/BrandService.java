package com.example.adminservice.service;

import com.example.adminservice.model.Brand;
import com.example.adminservice.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

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

    @Transactional
    public ResponseEntity<String> deleteBrand(String name){
       try {
           Brand b = brandRepository.findBrand(name);
           brandRepository.delete(b);
           return new ResponseEntity<>("brand deleted", HttpStatus.OK);
       }
       catch (Exception e){
           e.printStackTrace();
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
