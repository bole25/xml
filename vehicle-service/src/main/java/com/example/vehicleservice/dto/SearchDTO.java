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
    
}
