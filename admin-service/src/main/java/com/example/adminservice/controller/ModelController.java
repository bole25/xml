package com.example.adminservice.controller;

import com.example.adminservice.dto.ModelDTO;
import com.example.adminservice.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    ModelService modelService;

    @PostMapping("/create")
    public ResponseEntity<String> createModel(@RequestBody ModelDTO modelDTO){
        return modelService.createModel(modelDTO.getBrandName(), modelDTO.getModelName());
    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<String> deleteModel(@PathVariable("name") String name){
        return modelService.deleteModel(name);
    }
}
