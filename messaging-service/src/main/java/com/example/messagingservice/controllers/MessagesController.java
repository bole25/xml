package com.example.messagingservice.controllers;

import com.example.messagingservice.dto.MessageDTO;
import com.example.messagingservice.model.Message;
import com.example.messagingservice.repository.UserMessagesRepository;
import com.example.messagingservice.services.MessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    Logger logger = LoggerFactory.getLogger(MessagesController.class);

    @GetMapping("/sent")
    public ResponseEntity<Set<Message>> getSentMessages(@RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} je zatrazio poruke koje je poslao. {}", username, LocalDateTime.now());
        Set<Message> sent_messages = messagesService.getSentMessages(username);
        if(sent_messages == null){
            logger.error("Korisnik {} nije dobio poruke koje je poslao. {}", username, LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            logger.error("Korisnik {} je dobio poruke koje je poslao. {}", username, LocalDateTime.now());
            return new ResponseEntity<>(sent_messages,HttpStatus.OK);
        }
    }

    @GetMapping("/received")
    public ResponseEntity<Set<Message>> getReceivedMessages(@RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} je zatrazio poruke u inbox-u. {}", username, LocalDateTime.now());
        Set<Message> received_messages = messagesService.getReceivedMessages(username);
        if(received_messages == null){
            logger.error("Korisnik {} nije dobio inbox. {}", username, LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            logger.info("Korisnik {} je dobio inbox. {}", username, LocalDateTime.now());
            return new ResponseEntity<>(received_messages,HttpStatus.OK);
        }
    }

    //TODO TESTIRAJ OVO
    @PostMapping()
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO, @RequestHeader(value = "Username") String username){
        //TODO proveri da li sender i receiver imaju "RESERVED" request
        boolean success = messagesService.sendMessage(username, messageDTO);
        if(!success){
            logger.warn("Korisniku {} nije dozvoljeno da posalje poruku za korisnika {}. {}", username, messageDTO.getReceiver(), LocalDateTime.now());
            return new ResponseEntity<>(username + " is not allowed to send a message to " + messageDTO.getText(),HttpStatus.BAD_REQUEST);
        }
        logger.info("Korisnik {} je uspjesno poslao poruku za korisnika {}. {}", username, messageDTO.getReceiver(), LocalDateTime.now());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
