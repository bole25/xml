package com.example.adminservice.service;

import com.example.adminservice.model.Brand;
import com.example.adminservice.model.Transmission;
import com.example.adminservice.repository.BrandRepository;
import com.example.adminservice.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

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


    @Transactional
    public ResponseEntity<String> deleteTransmission(String name){
        try {
            transmissionRepository.deleteByName(name);
            return new ResponseEntity<>("transmission "+name+" deleted", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Set<Transmission>> getAll() {
        try {
            Set<Transmission> ret = transmissionRepository.getAll();
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
