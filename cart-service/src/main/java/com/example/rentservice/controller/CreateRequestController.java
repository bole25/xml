package com.example.rentservice.controller;

import com.example.rentservice.DTO.PurchaseCartDto;
import com.example.rentservice.model.Cart;
import com.example.rentservice.model.CartItem;
import com.example.rentservice.service.CartService;
import com.example.rentservice.service.CreateRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("request")
public class CreateRequestController {

    @Autowired
    CreateRequestsService requestsService;

    @PostMapping
    public ResponseEntity<String> createRequests(@RequestBody PurchaseCartDto bundle, @RequestHeader(value = "Username") String username){
        boolean success = requestsService.createRequests(bundle, username);

        if(success){
            return new ResponseEntity<>("", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}
