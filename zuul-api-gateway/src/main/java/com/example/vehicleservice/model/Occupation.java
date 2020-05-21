package com.example.vehicleservice.model;

import java.util.Date;

public class Occupation {

    private Long id;

    private Long user_id;
    private Vehicle vehicle;
    private Date startDate;
    private Date endDate;

    public Occupation() {
    }

    public Occupation(Long user_id, Vehicle vehicle, Date startDate, Date endDate) {
        this.user_id = user_id;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
