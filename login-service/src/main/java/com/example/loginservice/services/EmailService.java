package com.example.loginservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;


@Service
@CrossOrigin( allowCredentials= "true")
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;


	@Async
	public void sendEmailWithValidationCode(String username, int validationCode) throws MailException, InterruptedException {

		
		System.out.println("Slanje emaila za validaciju promene sifre...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("isapswgrupa11@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("CarShare validation code");
		mail.setText("Hello " + username + " your validation code is: " + validationCode);
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

}
