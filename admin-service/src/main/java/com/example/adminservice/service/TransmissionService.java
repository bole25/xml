package com.example.adminservice.service;

import com.example.adminservice.controller.BrandController;
import com.example.adminservice.model.Brand;
import com.example.adminservice.model.Transmission;
import com.example.adminservice.repository.BrandRepository;
import com.example.adminservice.repository.TransmissionRepository;
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
public class TransmissionService {
    @Autowired
    TransmissionRepository transmissionRepository;

    Logger logger = LoggerFactory.getLogger(TransmissionService.class);

    public ResponseEntity<String> createTransmission(Transmission t,String username){
        try{
            transmissionRepository.save(t);
            logger.info("Admin {} je kreirao tip mjenjaca {} za brend {}. {}", username, t.getName() , LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Neuspjesno kreiranje tipa mjenjaca {} od strane admina {}. {}",t.getName(), username, LocalDateTime.now());
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }

    }


    @Transactional
    public ResponseEntity<String> deleteTransmission(String name, String username){
        try {
            transmissionRepository.deleteByName(name);
            logger.info("Admin {} je obrisao tip mjenjaca {}. {}", username, name, LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Neuspjesno brisanje tipa mjenjaca {} od strane admina {}. {}", name, username, LocalDateTime.now());
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
