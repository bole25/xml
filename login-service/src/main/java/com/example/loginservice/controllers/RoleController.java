package com.example.loginservice.controllers;

import com.example.loginservice.model.UserCredentials;
import com.example.loginservice.repository.LoginCredentialsRepository;
import com.example.loginservice.services.ManageCredentialsService;
import com.example.loginservice.services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/role")
public class RoleController {

    @Autowired
    private ManageCredentialsService manageCredentialsService;

    @GetMapping("/{username}")
    public ResponseEntity<String> getUserRole(@PathVariable String username){
        UserCredentials uc = manageCredentialsService.getCredentials(username);
        if(uc == null){
            return new ResponseEntity<>("None", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(uc.getRole().toString(), HttpStatus.OK);
    }
}
