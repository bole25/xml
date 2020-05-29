package com.example.zuulservice.model;

import java.util.HashSet;
import java.util.Set;

public class Vehicle {

    private Long id;

    private String brand;
    private String model;
    private String fuel_type;
    private String transmission;
    private String vehicle_class;
    private Double price;
    private Integer mileage;

    //Odnosi se na to da li automobil ima ogranicenu kilometrazu koju moze da predje prilikom rentanja
    private Boolean limited_rent_mileage;

    //Polje koje oznacava da li je moguca kupovina protekcije za smanjenje troskova u slucaju nezgode
    private Boolean collision_damage_waiver;

    private Integer child_seat;

    private Set<Occupation> occupations;

    public Vehicle() {
        this.occupations = new HashSet<>();
    }

    public Vehicle(String brand, String model, String fuel_type, String transmission, String vehicle_class, Double price, Integer mileage, Boolean limited_rent_mileage, Boolean collision_damage_waiver, Integer child_seat) {
        this.brand = brand;
        this.model = model;
        this.fuel_type = fuel_type;
        this.transmission = transmission;
        this.vehicle_class = vehicle_class;
        this.price = price;
        this.mileage = mileage;
        this.limited_rent_mileage = limited_rent_mileage;
        this.collision_damage_waiver = collision_damage_waiver;
        this.child_seat = child_seat;
        this.occupations = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getVehicle_class() {
        return vehicle_class;
    }

    public void setVehicle_class(String vehicle_class) {
        this.vehicle_class = vehicle_class;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Boolean getLimited_rent_mileage() {
        return limited_rent_mileage;
    }

    public void setLimited_rent_mileage(Boolean limited_rent_mileage) {
        this.limited_rent_mileage = limited_rent_mileage;
    }

    public Boolean getCollision_damage_waiver() {
        return collision_damage_waiver;
    }

    public void setCollision_damage_waiver(Boolean collision_damage_waiver) {
        this.collision_damage_waiver = collision_damage_waiver;
    }

    public Integer getChild_seat() {
        return child_seat;
    }

    public void setChild_seat(Integer child_seat) {
        this.child_seat = child_seat;
    }

    public Set<Occupation> getOccupations() {
        return occupations;
    }

    public void setOccupations(Set<Occupation> occupations) {
        this.occupations = occupations;
    }
}
