package com.example.loginservice.controllers;

import com.example.loginservice.model.UserCredentials;
import com.example.loginservice.services.ManageCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageCredentialsController {

    @Autowired
    ManageCredentialsService credentialsService;

    @PostMapping("/addCredentials")
    protected ResponseEntity<Void> addCredentials(@RequestBody UserCredentials credentials){

        boolean added = credentialsService.addCredentials(credentials);

        if(added){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
    }
}
