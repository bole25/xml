package com.example.rentservice.DTO.request_creation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RequestDTO {

    private String owner_username;

    private Set<VehicleDTO> vehicles;
}
