package com.example.vehicleservice.dto;

import java.util.Date;

public class SearchByCompanyUsernameDTO {

	private Date from;
	private Date to;
	private String username;
	
	public SearchByCompanyUsernameDTO(Date from, Date to, String username) {
		super();
		this.from = from;
		this.to = to;
		this.username = username;
	}
	public SearchByCompanyUsernameDTO() {
		super();
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
