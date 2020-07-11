package com.example.vehicleservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReservationDTO {

	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date start;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
	
	public ReservationDTO() {
		
	}
	
}
