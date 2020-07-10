package com.example.adminservice.controller;

import com.example.adminservice.model.Brand;
import com.example.adminservice.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    Logger logger = LoggerFactory.getLogger(BrandController.class);
    @PostMapping()
    public ResponseEntity<String> createBrand(@RequestBody Brand brand, @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio kreiranje brenda {}. {}", username, brand.getName(), LocalDateTime.now());
        return brandService.createBrand(brand, username);
    }

    @PostMapping("/{name}")
    public ResponseEntity<String> deleteBrand(@PathVariable("name") String name, @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio brisanje brenda {}. {}", username, name, LocalDateTime.now());
        return brandService.deleteBrand(name,username);
    }

    @GetMapping()
    public ResponseEntity<Set<Brand>> getAll(){
        return brandService.getAll();
    }
}
