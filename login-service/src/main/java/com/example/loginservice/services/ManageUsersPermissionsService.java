package com.example.loginservice.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.loginservice.dto.UserCredentialsDTO;
import com.example.loginservice.model.UserCredentials;
import com.example.loginservice.repository.ManageUsersPermissionsRepository;

@Service
public class ManageUsersPermissionsService {

	@Autowired
	ManageUsersPermissionsRepository manageUsersRepo;

	public ResponseEntity<Void> blockUser(String username) {
		UserCredentials user = manageUsersRepo.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		user.setActive(false);
		user = manageUsersRepo.save(user);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	public ResponseEntity<Void> activateUsers(String username) {
		UserCredentials user = manageUsersRepo.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		user.setActive(true);
		user = manageUsersRepo.save(user);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	public ResponseEntity<Set<UserCredentialsDTO>> getAllByActivationCondition(Boolean isActive) {
		Set<UserCredentials> users = manageUsersRepo.getAllByActivationCondition(isActive);
		Set<UserCredentialsDTO> usersDTO = new HashSet<UserCredentialsDTO>(); 
		if(users == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		for(UserCredentials u : users) {
			usersDTO.add(new UserCredentialsDTO(u));
		}
		
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	
	
}
