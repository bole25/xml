package com.example.vehicleservice.dto;

import com.example.vehicleservice.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

	private Long id;
    private String brand;
    private String model;
    private String fuel_type;
    private String transmission;
    private String vehicle_class;
    private Double price;
    private Integer mileage;
    private String companyUsername;
    
    //Datum od kada je moguce iznajmiti vozilo
    private Date startDate;

    //Datum do kada je moguce iznajmiti vozilo
    private Date endDate;

    //Odnosi se na to da li automobil ima ogranicenu kilometrazu koju moze da predje prilikom rentanja
    private Boolean limited_rent_mileage;

    //ukoliko je limited_rent_mileage true tj ako je kilometraza ogranicena allowed mileage predstavlja koja je gornja granica
    private Integer allowed_mileage;

    //Polje koje oznacava da li je moguca kupovina protekcije za smanjenje troskova u slucaju nezgode
    private Boolean collision_damage_waiver;

    private Integer child_seat;

    private Set<String> images;


    public VehicleDTO(Vehicle v) {
        this.allowed_mileage = v.getAllowed_mileage();
        this.brand = v.getBrand();
        this.child_seat = v.getChild_seat();
        this.collision_damage_waiver = v.getCollision_damage_waiver();
        this.companyUsername = v.getCompanyUsername();
        this.endDate = v.getEndDate();
        this.fuel_type = v.getFuel_type();
        this.id = v.getId();
        this.images = new HashSet<>();
        this.limited_rent_mileage = v.getLimited_rent_mileage();
        this.mileage = v.getMileage();
        this.model = v.getModel();
        this.price = v.getPrice();
        this.startDate = v.getStartDate();

    }
}
