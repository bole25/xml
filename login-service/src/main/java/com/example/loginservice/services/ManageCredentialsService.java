package com.example.loginservice.services;


import com.example.loginservice.enumeration.RoleEnum;
import com.example.loginservice.model.UserCredentials;
import com.example.loginservice.repository.LoginCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageCredentialsService {

    @Autowired
    LoginCredentialsRepository credentialsRepository;

    public boolean addCredentials(UserCredentials credentials){

    	// izmeni da bude promenljiva rola
        credentials.setRole(RoleEnum.ROLE_CLIENT);
        credentials.setActive(Boolean.TRUE);
        UserCredentials returned_user = credentialsRepository.findByUsername(credentials.getUsername());
        credentials = credentialsRepository.save(credentials);

        System.out.println("usao");
        return true;
    }
    public boolean addCompany(UserCredentials credentials){

    	// izmeni da bude promenljiva rola
        credentials.setRole(RoleEnum.ROLE_COMPANY);
        credentials.setActive(Boolean.TRUE);
        credentials = credentialsRepository.save(credentials);

        return true;
    }
}
