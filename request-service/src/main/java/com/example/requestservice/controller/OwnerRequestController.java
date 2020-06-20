package com.example.requestservice.controller;

import com.example.requestservice.model.Request;
import com.example.requestservice.service.OwnerRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/owner/requests")
public class OwnerRequestController {

    @Autowired
    private OwnerRequestsService ownerRequestsService;

    @GetMapping("/pending")
    public ResponseEntity<Set<Request>> getPendingRequests(@RequestHeader(value = "Username") String username){
        Set<Request> pending = ownerRequestsService.getPending(username);
        if(pending==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pending, HttpStatus.OK);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<Set<Request>> getUpcomingRequests(@RequestHeader(value = "Username") String username){
        Set<Request> upcoming = ownerRequestsService.getUpcoming(username);
        if(upcoming==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(upcoming, HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable String id,@RequestHeader(value = "Username") String username){
        Long long_id;
        try{
            long_id = Long.parseLong(id);
        }catch(NumberFormatException e){
            return new ResponseEntity<>("Id format is not valid", HttpStatus.BAD_REQUEST);
        }
        boolean successful = ownerRequestsService.approveRequest(username, long_id);
        if(!successful){
            return new ResponseEntity<>("Request with that id does not exist, or isn't owned by logged in user",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<String> rejectRequest(@PathVariable String id,@RequestHeader(value = "Username") String username){
        Long long_id;
        try{
            long_id = Long.parseLong(id);
        }catch(NumberFormatException e){
            return new ResponseEntity<>("Id format is not valid", HttpStatus.BAD_REQUEST);
        }
        boolean successful = ownerRequestsService.rejectRequest(username, long_id);
        if(!successful){
            return new ResponseEntity<>("Request with that id does not exist, or isn't owned by logged in user",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
