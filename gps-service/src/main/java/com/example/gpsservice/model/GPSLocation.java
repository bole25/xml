package com.example.gpsservice.model;
/*
 * GPSLocation will be paired to vehicle 1 to 1, so every car will have its unique location all the time 
 * */
public class GPSLocation {
	private Long id;
	
	private Long longitude;
	private Long latitude;
	
	public GPSLocation() {
		super();
	}
	public GPSLocation(Long longitude, Long latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public Long getLongitude() {
		return longitude;
	}
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	public Long getLatitude() {
		return latitude;
	}
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
