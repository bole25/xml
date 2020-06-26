package com.example.registerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.registerservice.dto.UserResetPasswordDTO;
import com.example.registerservice.model.Validation;
import com.example.registerservice.services.MyUserDetailService;
import com.example.registerservice.services.RegisterService;

@RestController
@RequestMapping("/resetPassword")
public class ResetPasswordController {

	@Autowired
	MyUserDetailService userService;
	@Autowired
	RegisterService registerService;
	
    @PostMapping()
    public ResponseEntity<String> addRegistrationRequest(@RequestBody UserResetPasswordDTO user) throws MailException, InterruptedException{
    	userService.createResetPasswordRequest(user);
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @PutMapping()
    public ResponseEntity<String> validateUser(@RequestBody Validation sentValidation){
    	UserResetPasswordDTO user = userService.validateUser(sentValidation);
    	
    	if(user == null) {
    		return new ResponseEntity<>("Username does not exist, or validation code is not correct", HttpStatus.BAD_REQUEST);
    	}
    	
    	boolean success = registerService.changePassword(user);
    	
    	if(success) {
    		return new ResponseEntity<>("", HttpStatus.OK);
    	} else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    	}
    }
}
