package com.example.loginservice.controllers;

import com.example.loginservice.enumeration.RoleEnum;
import com.example.loginservice.model.UserCredentials;
import com.example.loginservice.model.UserPermission;
import com.example.loginservice.repository.LoginCredentialsRepository;
import com.example.loginservice.repository.UserPermissionRepository;
import com.example.loginservice.services.ManageCredentialsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageCredentialsController {

    @Autowired
    ManageCredentialsService credentialsService;

    @Autowired
    LoginCredentialsRepository credentialsRepository;
    
    @Autowired
    UserPermissionRepository permRepository;
    
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

    @GetMapping("havePermission/{perm}")
    protected ResponseEntity<Boolean> getPermission(@PathVariable String perm,
            @RequestHeader(value = "Username") String sender){
    	UserCredentials user = credentialsRepository.findByUsername(sender);
    	if(user.getRole() != RoleEnum.ROLE_CLIENT) {
    		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    	}
    	
    	UserPermission permission = permRepository.findByUserId(user.getId());
    	if(perm.equals("1")) {
    		if(!permission.getVehiclePerm()) {
    			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
    		} 
    	}else if(perm.equals("2")) {
    		if(!permission.getRequestPerm()) {
    			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
    		}
    	}else {
    		if(!permission.getOtherPerm()) {
    			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
    		}
    	}
    	
    	return new ResponseEntity<Boolean>(true,HttpStatus.OK);
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
