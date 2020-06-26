package com.example.adminservice.controller;

import com.example.adminservice.dto.ModelDTO;
import com.example.adminservice.model.FuelType;
import com.example.adminservice.model.Model;
import com.example.adminservice.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    ModelService modelService;

    Logger logger = LoggerFactory.getLogger(ModelController.class);

    @PostMapping()
    public ResponseEntity<String> createModel(@RequestBody ModelDTO modelDTO, @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio kreiranje modela {} za brend{}. {}", username, modelDTO.getModelName(), modelDTO.getBrandName(), LocalDateTime.now());
        return modelService.createModel(modelDTO.getBrandName(), modelDTO.getModelName(), username);
    }

    @PostMapping("/{name}")
    public ResponseEntity<String> deleteModel(@PathVariable("name") String name, @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio brisanje modela {}. {}", username, name, LocalDateTime.now());
        return modelService.deleteModel(name, username);
    }

    @GetMapping()
    public ResponseEntity<Set<Model>> getAll(){
        return modelService.getAll();
    }
}
