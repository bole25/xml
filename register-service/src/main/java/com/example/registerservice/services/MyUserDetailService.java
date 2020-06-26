package com.example.registerservice.services;

import java.time.LocalTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.registerservice.dto.UserResetPasswordDTO;
import com.example.registerservice.model.ResetPasswordRequest;
import com.example.registerservice.model.Validation;
import com.example.registerservice.repository.ResetPasswordRequestRepository;

@Service
public class MyUserDetailService {

	@Autowired
	ResetPasswordRequestRepository requestRepository;
	
	@Autowired
	EmailService emailService;
	
    
    public ResetPasswordRequest getRequest(String username) {
    	return requestRepository.getRequestByUsername(username);
    }
    
    
	public boolean createResetPasswordRequest(UserResetPasswordDTO user) throws MailException, InterruptedException{
		ResetPasswordRequest request = new ResetPasswordRequest();
		request.setPassword(user.getPassword());
		request.setUsername(user.getUsername());
		
		Random rand = new Random();
		int validationCode = 1000 + rand.nextInt(8999);		
		request.setValidationCode(validationCode);
		//sendEmail(validationCode, user.getUsername());
		emailService.sendEmailWithValidationCode(user.getUsername(), validationCode);
		request.setValidationTime(LocalTime.now());
		
		requestRepository.save(request);
		
		return true;
	}
    
	public UserResetPasswordDTO validateUser(Validation sentValidation) {
		ResetPasswordRequest request = requestRepository.getRequestByUsername(sentValidation.getUsername());
    	if(request == null) {
    		System.out.println(sentValidation.getUsername());
    		return null;
    	}
    	if(request.getValidationCode() != sentValidation.getValidationCode()) {
    		return null;
    	}
    	UserResetPasswordDTO user = new UserResetPasswordDTO();
    	user.setUsername(request.getUsername());
    	user.setPassword(request.getPassword());
    	
    	requestRepository.delete(request);
    	
    	/*
    	LocalTime time = LocalTime.now();
    	time = time.minusMinutes(5);
    	if(time.isAfter(request.getValidationTime())) {
    		return false;
    	}*/
    	
    	return user;


	}
}
