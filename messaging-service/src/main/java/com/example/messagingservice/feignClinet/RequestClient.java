package com.example.messagingservice.feignClinet;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("request-service")
public interface RequestClient {

    //Check if sender and receiver are connected via RESERVED request
    @GetMapping(value = "requests/areConnected/{receiver}", headers = {"Username={sender}"})
    Boolean checkIfConencted(@PathVariable("sender") String sender, @PathVariable("receiver") String receiver);
}
