package com.example.requestservice.rabbit;

import com.example.requestservice.dto.RejectRequestsDTO;
import com.example.requestservice.dto.request_creation.RequestsDTO;
import com.example.requestservice.service.RequestsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class QueueConsumerReject {

    @Autowired
    RequestsService requestsService;

    public void receiveMessage(String message){
        System.out.println("Usao u receiveMessage Reject sa stringom");
        System.out.println(message);
        Gson gson = new Gson();
        message = message.substring(1,message.length()-1);
        message = message.replace("\\","");
        RejectRequestsDTO requests = gson.fromJson(message, RejectRequestsDTO.class);
        requestsService.automaticallyDeclineRequests(requests);
    }

    public void receiveMessage(byte[] message) {
        System.out.println("Usao u receiveMessage Reject sa byteom");
    }

}
