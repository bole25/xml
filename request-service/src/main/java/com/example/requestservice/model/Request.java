package com.example.requestservice.model;

import com.example.requestservice.dto.request_creation.RequestDTO;
import com.example.requestservice.dto.request_creation.VehicleDTO;
import com.example.requestservice.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request", namespace = "http://localhost:8085/owner/requests/pending")
@XmlRootElement(name = "requestClass")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @Column(nullable = false)
    @XmlElement
    private String owner_username;

    @Column(nullable = true)
    private Double price;
    
    @Column(nullable = false)
    @XmlElement
    private RequestStatus status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @XmlElement
    private Set<Vehicle> vehicles;

    @Column
    private Date created = new Date();

    public Request(RequestDTO request){
        this.status = RequestStatus.PENDING;
        this.setOwner_username(request.getOwner_username());
        this.vehicles = new HashSet<>();
        for(VehicleDTO vehicle:request.getVehicles()){
            vehicles.add(new Vehicle(vehicle));
        }
        this.price = request.getPrice();
    }
}
