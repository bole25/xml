package com.example.loginservice.controllers;

import com.example.loginservice.enumeration.RoleEnum;
import com.example.loginservice.model.UserCredentials;
import com.example.loginservice.services.ManageCredentialsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageCredentialsController {

    @Autowired
    ManageCredentialsService credentialsService;

    @PostMapping("/addCredentials")
    protected ResponseEntity<Void> addCredentials(@RequestBody String encodedUser){
    	Gson gson = new Gson();
    	Type type = new TypeToken<UserCredentials>(){}.getType();
    	UserCredentials credentials = gson.fromJson(encodedUser, type);

        boolean added = credentialsService.addCredentials(credentials);

        if(added){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
    }
    
    @PostMapping("/addCompany")
    protected ResponseEntity<Void> addCompany(@RequestBody String encodedUser){
    	
    	Gson gson = new Gson();
    	Type type = new TypeToken<UserCredentials>(){}.getType();
    	UserCredentials credentials = gson.fromJson(encodedUser, type);
    	credentials.setRole(RoleEnum.ROLE_COMPANY);
        boolean added = credentialsService.addCompany(credentials);

        if(added){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
    }
}
