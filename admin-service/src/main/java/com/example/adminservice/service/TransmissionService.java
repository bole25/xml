package com.example.adminservice.service;

import com.example.adminservice.model.Brand;
import com.example.adminservice.model.Transmission;
import com.example.adminservice.repository.BrandRepository;
import com.example.adminservice.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransmissionService {
    @Autowired
    TransmissionRepository transmissionRepository;

    public ResponseEntity<String> createTransmission(Transmission t){
        try{
            transmissionRepository.save(t);
            return new ResponseEntity<>("Transmission created", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

    }
}
