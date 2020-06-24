package com.example.registerservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.registerservice.dto.UserDTO;
import com.example.registerservice.feignclient.LoginClient;
import com.example.registerservice.model.RegistrationRequest;
import com.google.gson.Gson;

@Service
public class RegisterService {

	@Autowired
	private LoginClient loginClient;
	
	public boolean register(UserDTO user) {
		Gson gson = new Gson();
		String encodedJSON = gson.toJson(user);
		loginClient.update(encodedJSON);
		return true;
	}
}
