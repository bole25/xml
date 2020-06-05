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
    
    
}
