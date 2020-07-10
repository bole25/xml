package com.example.registerservice.services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.ClientResponse;

import javax.ws.rs.core.MediaType;


@Service
@CrossOrigin( allowCredentials= "true")
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;


	@Async
	public void sendEmailWithValidationCode(String username, int validationCode) throws MailException, InterruptedException {

		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", "534f5680f35ce18a0387551022589f9b-87c34c41-4b197ed0"));
		WebResource webResource = client.resource("https://api.mailgun.net/v3/sandboxf0ca6ee889d04510b553592b7cc7e41c.mailgun.org/messages");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("from", " Supercool XML Project <postmaster@sandboxf0ca6ee889d04510b553592b7cc7e41c.mailgun.org>");
		formData.add("to", "Veljko Maksimovic <veljko.maksimovic@uns.ac.rs>");
		formData.add("subject", "Activation code");
		formData.add("text", "Hi, "+username+". Your activation code is "+validationCode);
		try {
			webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
					post(ClientResponse.class, formData);
		}catch (Exception e){
			e.printStackTrace();
		}
		/*
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("isapswgrupa11@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("CarShare validation code");
		mail.setText("Hello " + username + " your validation code is: " + validationCode);
		javaMailSender.send(mail);

		System.out.println("Email poslat!");*/
	}

}
