package com.example.gpsservice.rabbit;

import com.example.gpsservice.service.CoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    private CoordinatesService coordinatesService;

    public void receiveMessage(String message){
        System.out.println("Usao u receiveMessage sa stringom");
        System.out.println(message);
    }

    public void receiveMessage(byte[] message) {
        String message_str = new String(message);
        System.out.println(message_str);
        String info[] = message_str.split(" ");
        coordinatesService.updateCoordinates(Long.parseLong(info[0]),Double.parseDouble(info[1]),Double.parseDouble(info[2]));
    }

}
