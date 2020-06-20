package com.example.requestservice.controller;

import com.example.requestservice.dto.request_creation.RequestDTO;
import com.example.requestservice.model.Request;
import com.example.requestservice.service.RequestsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

@RestController()
@RequestMapping("/requests")
public class RequestsController {

    @Autowired
    RequestsService requestsService;

    @GetMapping()
    public ResponseEntity<Set<Request>> getRequests(@PathVariable String username){
        Set<Request> approved = requestsService.getApprovedRequests(username);
        if(approved==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(requestsService.getApprovedRequests(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRequests(@RequestBody String encodedRequests, @RequestHeader(value = "Username") String username){
        Gson gson = new Gson();
        Type type = new TypeToken<HashSet<RequestDTO>>(){}.getType();
        Set<RequestDTO> requests = gson.fromJson(encodedRequests,type);
        requestsService.createRequests(requests, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
