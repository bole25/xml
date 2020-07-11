package com.example.vehicleservice.rabbit;

import com.example.vehicleservice.dto.RejectRequestsDTO;
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

    public void produce(RejectRequestsDTO rejectRequestsDTO) throws JsonProcessingException {
        System.out.println("Usao u produce");
        rabbitTemplate.setExchange(fanoutExchange);
        Gson gson = new Gson();
        rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(gson.toJson(rejectRequestsDTO)));
        System.out.println("Zavrsio produce");
    }
}
