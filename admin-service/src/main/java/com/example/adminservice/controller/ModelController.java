package com.example.adminservice.controller;

import com.example.adminservice.dto.ModelDTO;
import com.example.adminservice.model.FuelType;
import com.example.adminservice.model.Model;
import com.example.adminservice.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    ModelService modelService;

    @PostMapping()
    public ResponseEntity<String> createModel(@RequestBody ModelDTO modelDTO){
        return modelService.createModel(modelDTO.getBrandName(), modelDTO.getModelName());
    }

    @DeleteMapping("/{name}")
    @Transactional
    public ResponseEntity<String> deleteModel(@PathVariable("name") String name){
        return modelService.deleteModel(name);
    }

    @GetMapping()
    public ResponseEntity<Set<Model>> getAll(){
        return modelService.getAll();
    }
}
