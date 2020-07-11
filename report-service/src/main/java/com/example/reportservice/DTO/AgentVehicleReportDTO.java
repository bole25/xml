package com.example.reportservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentVehicleReportDTO {

	private Integer km;
	private String comment;
	private Long vehicleId;
	
}
