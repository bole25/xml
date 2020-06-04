package com.example.adminservice.controller;

import com.example.adminservice.model.Brand;
import com.example.adminservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<String> createBrand(@RequestBody Brand brand){
        return brandService.createBrand(brand);
    }
}
