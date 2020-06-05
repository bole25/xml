package com.example.loginservice.dto;

import com.example.loginservice.model.UserCredentials;

public class UserCredentialsDTO {

	private String username;
	private Boolean active;
	
	public UserCredentialsDTO(UserCredentials u) {
		username = u.getUsername();
		active = u.getActive();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
