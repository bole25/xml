package com.example.adminservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.adminservice.service.GetInfoFromLoginService;

@RestController
@RequestMapping("/users")
public class ManageUsersPermissionsController {

	@Autowired
	private GetInfoFromLoginService infoService;

    Logger logger = LoggerFactory.getLogger(ManageUsersPermissionsController.class);
	
	@GetMapping("/block/{username}")
    public ResponseEntity<String> blockUsers(@PathVariable("username") String username){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String path = "/users/block/" + username;
        return new ResponseEntity<>(infoService.getData(path), headers, HttpStatus.OK);
    }
	@GetMapping("/activate/{username}")
    public ResponseEntity<String> activateUsers(@PathVariable("username") String username){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String path = "/users/activate/" + username;
        return new ResponseEntity<>(infoService.getData(path), headers, HttpStatus.OK);
    }
	@GetMapping("/{isActive}")
    public ResponseEntity<String> getAllByActivationCondition(@PathVariable("isActive") String isActive){

        HttpHeaders headers = new HttpHeaders();
        Boolean isActiveBool = Boolean.parseBoolean(isActive);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String path = "/users/getAllByActivationCondition/" + isActiveBool;
        return new ResponseEntity<>(infoService.getData(path), headers, HttpStatus.OK);
    }
}
