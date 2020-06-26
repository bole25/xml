package com.example.requestservice.controller;

import com.example.requestservice.model.Request;
import com.example.requestservice.service.OwnerRequestsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/owner/requests")
public class OwnerRequestController {

    @Autowired
    private OwnerRequestsService ownerRequestsService;

    Logger logger = LoggerFactory.getLogger(OwnerRequestController.class);

    @GetMapping("/pending")
    public ResponseEntity<Set<Request>> getPendingRequests(@RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} je zatrazio zahtjeve koje treba da odobri za njegova vozila. Vrijeme {}", username, LocalDateTime.now());
        Set<Request> pending = ownerRequestsService.getPending(username);
        if(pending==null){
            logger.error("Korisnik {} nije dobio zahtjeve koje treba da odobri. {}", username, LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        logger.info("Korisnik {} je uspjesno dobio zahtjeve koje treba da odobri. {}", username, LocalDateTime.now());
        return new ResponseEntity<>(pending, HttpStatus.OK);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<Set<Request>> getUpcomingRequests(@RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} je zatrazio zahtjeve koje je odobrio za njegova vozila. Vrijeme {}", username, LocalDateTime.now());
        Set<Request> upcoming = ownerRequestsService.getUpcoming(username);
        if(upcoming==null){
            logger.error("Korisnik {} nije dobio zahtjeve koje je odobrio. {}", username, LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        logger.info("Korisnik {} je uspjesno dobio zahtjeve koje je odobrio. {}", username, LocalDateTime.now());
        return new ResponseEntity<>(upcoming, HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable String id,@RequestHeader(value = "Username") String username){
        Long long_id;
        logger.info("Korisnik {} zatrazio da odobri zahtjev sa id-jem {}. {}", username, id, LocalDateTime.now());
        try{
            long_id = Long.parseLong(id);
        }catch(NumberFormatException e){
            return new ResponseEntity<>("Id format is not valid", HttpStatus.BAD_REQUEST);
        }
        boolean successful = ownerRequestsService.approveRequest(username, long_id);
        if(!successful){
            logger.error("Neuspjesno odobravanje zahtjeva sa id-jem {} od strane korisnika {}. {}", id, username, LocalDateTime.now());
            return new ResponseEntity<>("Request with that id does not exist, or isn't owned by logged in user",
                    HttpStatus.BAD_REQUEST);
        }
        logger.info("Korisnik {} je uspjesno odobrio zahtjev sa id-jem {} za vozilo. {}", username, id, LocalDateTime.now());
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<String> rejectRequest(@PathVariable String id,@RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} zatrazio da odbije zahtjev sa id-jem {}. {}", username, id, LocalDateTime.now());
        Long long_id;
        try{
            long_id = Long.parseLong(id);
        }catch(NumberFormatException e){
            return new ResponseEntity<>("Id format is not valid", HttpStatus.BAD_REQUEST);
        }
        boolean successful = ownerRequestsService.rejectRequest(username, long_id);
        if(!successful){
            logger.error("Neuspjesno odbijanje zahtjeva sa id-jem {} od strane korisnika {}. {}", id, username, LocalDateTime.now());
            return new ResponseEntity<>("Request with that id does not exist, or isn't owned by logged in user",
                    HttpStatus.BAD_REQUEST);
        }
        logger.info("Korisnik {} je uspjesno odbio zahtjev sa id-jem {} za vozilo. {}", username, id, LocalDateTime.now());
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
