package com.example.requestservice.model;

import com.example.requestservice.dto.request_creation.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vehicle_name;

    @Column(nullable = false)
    private Long vehicle_id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Occupation time_span;

    public Vehicle(VehicleDTO vehicleDTO){
        this.vehicle_name=vehicleDTO.getVehicle_name();
        this.vehicle_id=vehicleDTO.getVehicle_id();
        this.time_span = new Occupation(vehicleDTO.getTime_span().getStartDate(), vehicleDTO.getTime_span().getEndDate());
    }
}
