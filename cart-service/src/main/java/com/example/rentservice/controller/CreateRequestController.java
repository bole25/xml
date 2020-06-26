package com.example.rentservice.controller;

import com.example.rentservice.DTO.PurchaseCartDto;
import com.example.rentservice.model.Cart;
import com.example.rentservice.model.CartItem;
import com.example.rentservice.service.CartService;
import com.example.rentservice.service.CreateRequestsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/request")
public class CreateRequestController {

    @Autowired
    CreateRequestsService requestsService;

    Logger logger = LoggerFactory.getLogger(CreateRequestController.class);

    @PostMapping
    public ResponseEntity<String> createRequests(@RequestBody PurchaseCartDto bundle, @RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} trazi kreiranje zahtjeva za iznajmljivanje iz korpe. {}", username, LocalDateTime.now());
        boolean success = requestsService.createRequests(bundle, username);
        if(success){
            logger.info("Korisnik {} uspjesno kreirao zahtjeve za vozila iz korpe. {}", username, LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);
        }else{
            logger.error("Neuspjesno kreiranje zahtjeva za iznajmljivanje za korisnika {}. {}", username, LocalDateTime.now());
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}
