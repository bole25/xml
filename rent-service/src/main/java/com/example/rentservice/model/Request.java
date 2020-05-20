package com.example.rentservice.model;

import java.util.Date;

public class Request {

    private Long id;

    private Long user_id;
    private Boolean approved;
    private Vehicle vehicle;
    private Date startDate;
    private Date endDate;

    public Request() {
    }

    public Request(Long user_id, Vehicle vehicle, Date startDate, Date endDate) {
        this.user_id = user_id;
        this.approved = Boolean.FALSE;
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

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
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
