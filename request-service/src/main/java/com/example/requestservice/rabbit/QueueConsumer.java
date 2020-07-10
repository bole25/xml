package com.example.requestservice.rabbit;

import com.example.requestservice.dto.request_creation.RequestDTO;
import com.example.requestservice.dto.request_creation.RequestsDTO;
import com.example.requestservice.service.RequestsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.HashSet;

@Component
public class QueueConsumer {

    @Autowired
    RequestsService requestsService;

    public void receiveMessage(String message){
        System.out.println("Usao u receiveMessage sa stringom");
        System.out.println(message);
        Gson gson = new Gson();
        message = message.substring(1,message.length()-1);
        message = message.replace("\\","");
        RequestsDTO requests = gson.fromJson(message,RequestsDTO.class);
        requestsService.createRequests(requests.getRequests(),requests.getUsername());
    }

    public void receiveMessage(byte[] message) {
        System.out.println("Usao u receiveMessage sa byteom");
    }

}
