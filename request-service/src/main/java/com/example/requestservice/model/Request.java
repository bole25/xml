package com.example.requestservice.model;

import com.example.requestservice.dto.request_creation.RequestDTO;
import com.example.requestservice.dto.request_creation.VehicleDTO;
import com.example.requestservice.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String owner_username;

    @Column(nullable = false)
    private RequestStatus status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    public Request(RequestDTO request){
        this.status = RequestStatus.PENDING;
        this.setOwner_username(request.getOwner_username());
        this.vehicles = new HashSet<>();
        for(VehicleDTO vehicle:request.getVehicles()){
            vehicles.add(new Vehicle(vehicle));
        }
    }
}
