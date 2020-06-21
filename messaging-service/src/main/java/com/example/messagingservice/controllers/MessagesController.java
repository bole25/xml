package com.example.messagingservice.controllers;

import com.example.messagingservice.dto.MessageDTO;
import com.example.messagingservice.model.Message;
import com.example.messagingservice.repository.UserMessagesRepository;
import com.example.messagingservice.services.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    @GetMapping("/sent")
    public ResponseEntity<Set<Message>> getSentMessages(@RequestHeader(value = "Username") String username){
        Set<Message> sent_messages = messagesService.getSentMessages(username);
        if(sent_messages == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(sent_messages,HttpStatus.OK);
        }
    }

    @GetMapping("/received")
    public ResponseEntity<Set<Message>> getReceivedMessages(@RequestHeader(value = "Username") String username){
        Set<Message> received_messages = messagesService.getReceivedMessages(username);
        if(received_messages == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(received_messages,HttpStatus.OK);
        }
    }

    //TODO TESTIRAJ OVO
    @PostMapping()
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO, @RequestHeader(value = "Username") String username){
        //TODO proveri da li sender i receiver imaju "RESERVED" request
        boolean success = messagesService.sendMessage(username, messageDTO);
        if(!success){
            return new ResponseEntity<>("Sender or Receiver do not exist in database",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
