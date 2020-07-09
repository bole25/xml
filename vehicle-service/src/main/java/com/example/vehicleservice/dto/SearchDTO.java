package com.example.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    private String place;
    private Date startDate;
    private Date endDate;
    private String brand;
    private String model;
    private String gas;
    private String transmission;
    private String carClass;
    private int price1;
    private int price2;
    private int KMAmount;
    private int plannedKMAmount;
    private Boolean CDW;
    private int kidsSeats;

    public SearchDTO(Date from, Date to) {
		this.startDate = from;
		this.endDate = to;
	}
    

	@Override
    public String toString() {
        return "SearchDTO{" +
                "place='" + place + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }


	public SearchDTO(String brand, String model, String gas, String transmission, String carClass, int price1,
			int price2, int kMAmount, int plannedKMAmount, Boolean cDW, int kidsSeats) {
		super();
		this.brand = brand;
		this.model = model;
		this.gas = gas;
		this.transmission = transmission;
		this.carClass = carClass;
		this.price1 = price1;
		this.price2 = price2;
		KMAmount = kMAmount;
		this.plannedKMAmount = plannedKMAmount;
		CDW = cDW;
		this.kidsSeats = kidsSeats;
	}
    
}
