package com.example.vehicleservice.dto;

import com.example.vehicleservice.adapter.DateAdapter;
import com.example.vehicleservice.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vehicle", namespace = "http://localhost:8083/vehicle")
@XmlRootElement(name = "vehicleClass")
public class VehicleDTO {
    @XmlElement
    private Long id;
    @XmlElement
    private String brand;
    @XmlElement
    private String model;
    @XmlElement
    private String fuelType;
    @XmlElement
    private String transmission;
    @XmlElement
    private String vehicleClass;
    @XmlElement
    private Double price;
    @XmlElement
    private Integer mileage;
    @XmlElement
    private String companyUsername;
    
    //Datum od kada je moguce iznajmiti vozilo
    @XmlElement
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    //Datum do kada je moguce iznajmiti vozilo
    @XmlElement
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    //Odnosi se na to da li automobil ima ogranicenu kilometrazu koju moze da predje prilikom rentanja
    @XmlElement
    private Boolean limitedRentMileage;

    //ukoliko je limited_rent_mileage true tj ako je kilometraza ogranicena allowed mileage predstavlja koja je gornja granica
    @XmlElement
    private Integer allowedMileage;

    //Polje koje oznacava da li je moguca kupovina protekcije za smanjenje troskova u slucaju nezgode
    @XmlElement
    private Boolean collisionDamageWaiver;

    @XmlElement
    private Integer childSeat;

    @XmlElement
    private Set<String> images = new HashSet<>();


    public VehicleDTO(Vehicle v) {
        this.allowedMileage = v.getAllowed_mileage();
        this.brand = v.getBrand();
        this.childSeat = v.getChild_seat();
        this.collisionDamageWaiver = v.getCollision_damage_waiver();
        this.companyUsername = v.getCompanyUsername();
        this.endDate = v.getEndDate();
        this.fuelType = v.getFuel_type();
        this.id = v.getId();
        this.images = new HashSet<>();
        this.limitedRentMileage = v.getLimited_rent_mileage();
        this.mileage = v.getMileage();
        this.model = v.getModel();
        this.price = v.getPrice();
        this.startDate = v.getStartDate();

    }
}
