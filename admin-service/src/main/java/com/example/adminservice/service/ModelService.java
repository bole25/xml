package com.example.adminservice.service;

import com.example.adminservice.controller.BrandController;
import com.example.adminservice.model.Brand;
import com.example.adminservice.model.FuelType;
import com.example.adminservice.model.Model;
import com.example.adminservice.repository.BrandRepository;
import com.example.adminservice.repository.ModelRepository;
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
public class ModelService {

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    BrandRepository brandRepository;

    Logger logger = LoggerFactory.getLogger(ModelService.class);

    public ResponseEntity<String> createModel(String brandName, String modelName, String username){
        try{
            Brand br = brandRepository.findBrand(brandName);
            Model m = new Model(modelName);
            br.getModels().add(m);
            brandRepository.save(br);
            logger.info("Admin {} je kreirao model {} za brend {}. {}", username, modelName,brandName, LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);

        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Neuspjesno kreiranje modela {} za brend {} od strane admina {}. {}",modelName,brandName,username, LocalDateTime.now());
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<String> deleteModel(String name,String username){
        try {
            Brand b = brandRepository.findBrandForModelDelete(name);
            b.removeModel(name);
            brandRepository.save(b);
            modelRepository.deleteByName(name);
            logger.info("Admin {} je obrisao model {}. {}", username, name, LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Neuspjesno brisanje modela {} od strane admina {}. {}", name, username, LocalDateTime.now());
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Set<Model>> getAll(){
        try {
            Set<Model> ret = modelRepository.getAll();
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
