package com.example.loginservice.controllers;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginservice.dto.ChangePasswordDTO;
import com.example.loginservice.dto.UserResetPasswordDTO;
import com.example.loginservice.model.UserCredentials;
import com.example.loginservice.model.Validation;
import com.example.loginservice.services.ManageUsersPermissionsService;
import com.example.loginservice.services.MyUserDetailService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/password")
public class ChangePasswordController {
	@Autowired
	ManageUsersPermissionsService manageUsersService;
	
	@Autowired
	MyUserDetailService myUserDetailService;
	
	@PutMapping
	public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, @RequestHeader("Username") String username){
		boolean success = this.myUserDetailService.changePassword(username, changePasswordDTO);
		if(success) {
			return new ResponseEntity<Boolean>(success, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Boolean>(success, HttpStatus.FORBIDDEN);
		
	}
	

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePasswordString(@RequestBody String encodedUser){
    	Gson gson = new Gson();
    	Type type = new TypeToken<UserResetPasswordDTO>(){}.getType();
    	UserResetPasswordDTO user = gson.fromJson(encodedUser, type);
    	myUserDetailService.changePasswordString(user.getUsername(), user.getPassword());
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
	
    
	/*
    @PostMapping()
    public ResponseEntity<String> addRegistrationRequest(@RequestBody UserDTO user) throws MailException, InterruptedException{
    	myUserDetailService.createResetPasswordRequest(user);
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @PutMapping("/validate")
    public ResponseEntity<String> validateUser(@RequestBody Validation sentValidation){
    	boolean success = myUserDetailService.validateUser(sentValidation);
    	
    	if(!success) {
    		return new ResponseEntity<>("Username does not exist, or validation code is not correct", HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
	*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
