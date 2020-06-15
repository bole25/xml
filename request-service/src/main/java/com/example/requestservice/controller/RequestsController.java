package com.example.requestservice.controller;

import com.example.requestservice.model.Request;
import com.example.requestservice.service.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController()
@RequestMapping("/{username}")
public class RequestsController {

    @Autowired
    RequestsService requestsService;

    @GetMapping()
    public ResponseEntity<Set<Request>> getRequests(@PathVariable String username){
        System.out.println("Usao");
        Set<Request> approved = requestsService.getApprovedRequests(username);
        if(approved==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(requestsService.getApprovedRequests(username), HttpStatus.OK);
    }
}
