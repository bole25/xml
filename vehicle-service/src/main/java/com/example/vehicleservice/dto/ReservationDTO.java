package com.example.vehicleservice.dto;

import java.util.Date;

public class ReservationDTO {

	private Long id;
	private Date start;
	private Date end;
	public ReservationDTO(Long id, Date start, Date end) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	
}
