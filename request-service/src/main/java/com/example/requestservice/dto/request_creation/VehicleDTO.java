package com.example.requestservice.dto.request_creation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VehicleDTO {

    private String vehicle_name;

    private Long vehicle_id;

    private OccupationDTO time_span;
}
