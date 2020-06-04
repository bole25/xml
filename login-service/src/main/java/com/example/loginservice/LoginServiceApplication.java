package com.example.loginservice;

import com.example.loginservice.enumeration.RoleEnum;
import com.example.loginservice.model.UserCredentials;
import com.example.loginservice.repository.LoginCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginServiceApplication {

	public static void main(String[] args) { SpringApplication.run(LoginServiceApplication.class, args); }

}
