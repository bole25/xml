package com.example.requestservice.controller;

import com.example.requestservice.dto.request_creation.RequestDTO;
import com.example.requestservice.enums.RequestStatus;
import com.example.requestservice.model.Request;
import com.example.requestservice.service.RequestsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestController()
@RequestMapping("/requests")
public class RequestsController {

    @Autowired
    RequestsService requestsService;

    Logger logger = LoggerFactory.getLogger(RequestsController.class);

    @GetMapping("/approved")
    public ResponseEntity<Set<Request>> getApprovedRequests(@RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} je zatrazio zahtjeve koji su mu odobreni. {}", username, LocalDateTime.now());
        Set<Request> approved = requestsService.getRequests(username, RequestStatus.RESERVED);
        if(approved==null){
            logger.error("Korisnik {} nije dobio zahtjeve koji su mu odobreni. {}", username, LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        logger.info("Korisnik {} je uspjesno dobio zahtjeve koji su mu odobreni. {}", username, LocalDateTime.now());
        return new ResponseEntity<>(approved, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<Set<Request>> getPendingRequests(@RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} je zatrazio zahtjeve koji treba da mu se odobre (Na cekanju). {}", username, LocalDateTime.now());
        Set<Request> pending = requestsService.getRequests(username, RequestStatus.PENDING);
        if(pending==null){
            logger.error("Korisnik {} nije dobio zahtjeve koji treba da mu se odobre(Zahtjevi na cekanju). {}", username, LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        logger.info("Korisnik {} je uspjesno dobio zahtjeve koji treba da mu se odobre(Zahtjevi na cekanju). {}", username, LocalDateTime.now());
        return new ResponseEntity<>(pending, HttpStatus.OK);
    }

    @GetMapping("/rejected")
    public ResponseEntity<Set<Request>> getRejectedRequests(@RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} je zatrazio zahtjeve koji su mu odbijeni. {}", username, LocalDateTime.now());
        Set<Request> rejected = requestsService.getRequests(username, RequestStatus.REJECTED);
        if(rejected==null){
            logger.error("Korisnik {} nije dobio zahtjeve koji su mu odbijeni. {}", username, LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        logger.info("Korisnik {} je dobio zahtjeve koji su mu odbijeni. {}", username, LocalDateTime.now());
        return new ResponseEntity<>(rejected, HttpStatus.OK);
    }

    //TODO TESTIRATI
    @GetMapping("/finished")
    public ResponseEntity<Set<Request>> getFinishedRequests(@RequestHeader(value = "Username") String username){
        Set<Request> finished = requestsService.getFinished(username, RequestStatus.RESERVED);
        if(finished==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(finished, HttpStatus.OK);
    }

    //Check if logged in user is connected with the "receiver user", or in other words, do the share a request
    // that is "RESERVED"
    @GetMapping("areConnected/{receiver}")
    public ResponseEntity<Boolean> areConnected(@PathVariable String receiver,
                                               @RequestHeader(value = "Username") String sender){
        Boolean connected= requestsService.areConnected(sender, receiver);
        if(connected==null){
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
        }
        return new ResponseEntity<>(connected, HttpStatus.OK);
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
