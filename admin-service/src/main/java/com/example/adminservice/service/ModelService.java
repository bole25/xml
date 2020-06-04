package com.example.adminservice.service;

import com.example.adminservice.model.Brand;
import com.example.adminservice.model.Model;
import com.example.adminservice.repository.BrandRepository;
import com.example.adminservice.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    BrandRepository brandRepository;

    public ResponseEntity<String> createModel(String brandName, String modelName){
        try{
            Brand br = brandRepository.findBrand(brandName);
            Model m = new Model(modelName);
            br.getModels().add(m);
            brandRepository.save(br);
            return new ResponseEntity<>("Model created", HttpStatus.OK);

        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

}
