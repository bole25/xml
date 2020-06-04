package com.example.adminservice.service;

import com.example.adminservice.model.Model;
import com.example.adminservice.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    @Autowired
    ModelRepository modelRepository;

}
