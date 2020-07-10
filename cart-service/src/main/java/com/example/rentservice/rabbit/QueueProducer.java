package com.example.rentservice.rabbit;

import com.example.rentservice.DTO.PurchaseCartDto;
import com.example.rentservice.DTO.request_creation.RequestDTO;
import com.example.rentservice.DTO.request_creation.RequestsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {

    @Value("${fanout.exchange}")
    private  String fanoutExchange;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public QueueProducer(RabbitTemplate rabbitTemplate){
        super();
        this.rabbitTemplate=rabbitTemplate;
    }

    public void produce(RequestsDTO purchaseCartDto) throws JsonProcessingException {
        System.out.println("Usao u produce");
        rabbitTemplate.setExchange(fanoutExchange);
        Gson gson = new Gson();
        rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(gson.toJson(purchaseCartDto)));
        System.out.println("Zavrsio produce");
    }
}
