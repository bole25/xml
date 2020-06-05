package com.example.loginservice.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginservice.dto.UserCredentialsDTO;
import com.example.loginservice.services.ManageUsersPermissionsService;

@RestController
@RequestMapping("/users")
public class ManageUsersPermissionsController {

	@Autowired
	ManageUsersPermissionsService manageUsersService;
	
	@GetMapping("/block/{username}")
    public ResponseEntity<Void> blockUsers(@PathVariable("username") String username){
		return manageUsersService.blockUser(username);
    }
	
	@GetMapping("/activate/{username}")
    public ResponseEntity<Void> activateUsers(@PathVariable("username") String username){
		return manageUsersService.activateUsers(username);
    }
	@GetMapping("/getAllByActivationCondition/{isActive}")
    public ResponseEntity<Set<UserCredentialsDTO>> getAllByActivationCondition(@PathVariable("isActive") Boolean isActive){
		return manageUsersService.getAllByActivationCondition(isActive);
    }
	
}
