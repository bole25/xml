package com.example.messagingservice.model;

import com.example.messagingservice.enumeration.RoleEnum;

public class User {
private Long id;
	
	private String email;
	
	private String password;
	
	private RoleEnum role;

	public User() {
		super();
	}
	public User(String email, String password, RoleEnum role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RoleEnum getRole() {
		return role;
	}
	public void setRole(RoleEnum role) {
		this.role = role;
	}
}
