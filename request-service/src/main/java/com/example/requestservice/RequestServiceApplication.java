package com.example.requestservice;

import com.example.requestservice.scheduler.ProcessingClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class RequestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestServiceApplication.class, args);
	}

}
