package com.example.registerservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.registerservice.dto.UserDTO;
import com.example.registerservice.model.RegistrationRequest;
import com.example.registerservice.model.Validation;
import com.example.registerservice.repository.RegistrationRequstRepository;

import java.util.Random;
import java.time.LocalTime;



@Service
public class RegistrationRequestService {

	@Autowired
	RegistrationRequstRepository repository;
	
	@Autowired
	private EmailService emailService;
	
	public RegistrationRequest getRequest(String username) {
		return repository.getRequestByUsername(username);
	}
	
	public UserDTO validateUser(Validation sentValidation) {
    	RegistrationRequest request = repository.getRequestByUsername(sentValidation.getUsername());
    	if(request == null) {
    		return null;
    	}
    	if(request.getValidationCode() != sentValidation.getValidationCode()) {
    		return null;
    	}
    	UserDTO user = new UserDTO();
    	user.setActive(true);
    	user.setPassword(request.getPassword());
    	user.setUsername(request.getUsername());
    	user.setRole(request.getRole());
    	return user;
	}
	
	public boolean createRequest(UserDTO user) throws MailException, InterruptedException{
		RegistrationRequest request = new RegistrationRequest();
		request.setActive(false);
		request.setPassword(user.getPassword());
		request.setRole(user.getRole());
		request.setUsername(user.getUsername());
		
		Random rand = new Random();
		int validationCode = 1000 + rand.nextInt(8999);		
		request.setValidationCode(validationCode);
		//sendEmail(validationCode, user.getUsername());
		emailService.sendEmailWithValidationCode(user.getUsername(), validationCode);
		request.setValidationTime(LocalTime.now());
		
		repository.save(request);
		
		return true;
	}
	

	
	
	

}
