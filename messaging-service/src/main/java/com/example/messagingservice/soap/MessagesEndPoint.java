package com.example.messagingservice.soap;

import com.example.messagingservice.dto.MessageDTO;
import com.example.messagingservice.model.Message;
import com.example.messagingservice.services.MessagesService;
import com.example.messagingservice.soap.convert.InboxRequest;
import com.example.messagingservice.soap.convert.InboxResponse;
import com.example.messagingservice.soap.convert.SendRequest;
import com.example.messagingservice.soap.convert.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDateTime;
import java.util.Set;

@Endpoint
public class MessagesEndPoint {
    private static final String NAMESPACE_URI = "http://localhost:8088/messages";

    @Autowired
    MessagesService messagesService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "inboxRequest")
    @ResponsePayload
    public InboxResponse inboxResponse(@RequestPayload InboxRequest request){
        System.out.println("dobavljanje inboxa");
        InboxResponse response = new InboxResponse();
        Set<Message> messageSet = messagesService.getReceivedMessages(request.getUsername());
        for(Message msg : messageSet){
            com.example.messagingservice.soap.convert.Message m = new com.example.messagingservice.soap.convert.Message();
            m.setId(msg.getId());
            m.setTime(msg.getTime().toString());
            m.setTextMessage(msg.getTextMessage());
            m.setReceiverUsername(msg.getReceiver_username());
            m.setSenderUsername(msg.getSender_username());
            response.getMessages().add(m);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendRequest")
    @ResponsePayload
    public SendResponse sendResponse(@RequestPayload SendRequest request){
       try {
           SendResponse response = new SendResponse();
           MessageDTO messageDTO = new MessageDTO(request.getMessage().getTextMessage(), request.getMessage().getReceiverUsername());
           boolean success = messagesService.sendMessage(request.getUsername(), messageDTO);
           if(!success){
               response.setId(0);
           }
           else {
               response.setId(1);
           }

           return response;
       }
       catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }

}
