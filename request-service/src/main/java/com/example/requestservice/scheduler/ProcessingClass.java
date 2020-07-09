package com.example.requestservice.scheduler;

import com.example.requestservice.repository.RequestRepository;
import com.example.requestservice.service.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

@Component
public class ProcessingClass {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestsService requestsService;

    //Svaka 2h se provjeravaju zahtjevi
    //Testirano je sa 5000 (5s) 
    @Scheduled(fixedRate = 720000)
    public void reportCurrentTime() {
        // Ukoliko je od kreiranja zahtjeva proslo 24h i nije odobren automatski se odbija
        requestsService.removeAfter24h();
        System.out.println("Odbijeni svi zahtjevi koji nisu prihvaceni posle 24h");
    }


}
