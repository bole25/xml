package com.example.loginservice.services;

import com.example.loginservice.dto.ChangePasswordDTO;
import com.example.loginservice.dto.UserResetPasswordDTO;
import com.example.loginservice.model.ResetPasswordRequest;
import com.example.loginservice.model.UserCredentials;
import com.example.loginservice.model.Validation;
import com.example.loginservice.repository.ChangePasswordRequestRepository;
import com.example.loginservice.repository.LoginCredentialsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    LoginCredentialsRepository credentialsRepository;

    @Autowired
    ChangePasswordRequestRepository requestRepository;
    
    @Autowired
    EmailService emailService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCredentials user= credentialsRepository.findByUsername(username);

        if (user == null || user.getActive() == Boolean.FALSE) {
            throw new UsernameNotFoundException(username + "not found");
        }

        List<GrantedAuthority> authorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(user.getRole().name());
        return new User(username, encoder.encode(user.getPassword()), authorities);

    }
    
    public boolean changePassword(String username, ChangePasswordDTO changePasswordDTO) {
    	UserCredentials user = credentialsRepository.findByUsername(username);
    	if(user == null) {
    		System.out.println("Ne postoji user");
    		return false;
    	}
    	if(user == null || !user.getPassword().equals(changePasswordDTO.getOldPassword() )) {
    		return false;
    	} 

    	user.setPassword(changePasswordDTO.getNewPassword());
    	credentialsRepository.save(user);
    	return true;
    }
    
    public boolean changePasswordString(String username, String password) {
    	UserCredentials user = credentialsRepository.findByUsername(username);
    	if(user == null) {
    		System.out.println("Ne postoji user");
    		return false;
    	}

    	user.setPassword(password);
    	credentialsRepository.save(user);
    	return true;
    }
    /*
    public ResetPasswordRequest getRequest(String username) {
    	return requestRepository.getRequestByUsername(username);
    }
    
    
	public boolean createResetPasswordRequest(UserDTO user) throws MailException, InterruptedException{
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
    
	public boolean validateUser(Validation sentValidation) {
		ResetPasswordRequest request = requestRepository.getRequestByUsername(sentValidation.getUsername());
    	if(request == null) {
    		return false;
    	}
    	if(request.getValidationCode() != sentValidation.getValidationCode()) {
    		return false;
    	}

    	LocalTime time = LocalTime.now();
    	time = time.minusMinutes(5);
    	if(time.isAfter(request.getValidationTime())) {
    		return false;
    	}
    	
    	UserCredentials user = credentialsRepository.findByUsername(sentValidation.getUsername());
    	if(user == null) {
    		System.out.println("Ne postoji user");
    		return false;
    	}
    	

    	user.setPassword(request.getPassword());
    	credentialsRepository.save(user);
    	return true;
	}
    
    */
    
    
    
    
}
