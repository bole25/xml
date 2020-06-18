package com.example.adminservice.controller;

import com.example.adminservice.model.Brand;
import com.example.adminservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @PostMapping()
    public ResponseEntity<String> createBrand(@RequestBody Brand brand){
        return brandService.createBrand(brand);
    }

    @PostMapping("/{name}")
    @Transactional
    public ResponseEntity<String> deleteBrand(@PathVariable("name") String name){
        return brandService.deleteBrand(name);
    }

    @GetMapping()
    public ResponseEntity<Set<Brand>> getAll(){
        System.out.println("pogodjen admin endpoint");
        return brandService.getAll();
    }
}
