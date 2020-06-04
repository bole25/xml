package com.example.vehicleservice.dto;

import com.example.vehicleservice.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private String brand;
    private String model;
    private String fuel_type;
    private String transmission;
    private String vehicle_class;
    private Double price;
    private Integer mileage;
    private String companyUsername;
    
    //Datum od kada je moguce iznajmiti vozilo
    private String startDate;

    //Datum do kada je moguce iznajmiti vozilo
    private String endDate;

    //Odnosi se na to da li automobil ima ogranicenu kilometrazu koju moze da predje prilikom rentanja
    private Boolean limited_rent_mileage;

    //ukoliko je limited_rent_mileage true tj ako je kilometraza ogranicena allowed mileage predstavlja koja je gornja granica
    private Integer allowed_mileage;

    //Polje koje oznacava da li je moguca kupovina protekcije za smanjenje troskova u slucaju nezgode
    private Boolean collision_damage_waiver;

    private Integer child_seat;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	
	
	public String getCompanyUsername() {
		return companyUsername;
	}

	public void setCompanyUsername(String companyUsername) {
		this.companyUsername = companyUsername;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Boolean getLimited_rent_mileage() {
		return limited_rent_mileage;
	}

	public void setLimited_rent_mileage(Boolean limited_rent_mileage) {
		this.limited_rent_mileage = limited_rent_mileage;
	}

	public Integer getAllowed_mileage() {
		return allowed_mileage;
	}

	public void setAllowed_mileage(Integer allowed_mileage) {
		this.allowed_mileage = allowed_mileage;
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


}
