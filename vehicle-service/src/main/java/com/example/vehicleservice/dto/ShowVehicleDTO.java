package com.example.vehicleservice.dto;

import com.example.vehicleservice.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShowVehicleDTO {
    private String model;
    private String brand;
    private Double price;
    
    public ShowVehicleDTO(Vehicle v) {
    	model = v.getModel();
    	brand = v.getBrand();
    	price = v.getPrice();
    }

	public ShowVehicleDTO(String model2, String brand2, Double price2) {
		this.model = model2;
		this.brand = brand;
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
    
    
}
