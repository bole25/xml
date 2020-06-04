package com.example.vehicleservice.dto;

import com.example.vehicleservice.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private String brand;
    private String model;
    private String fuel_type;
    private String transmission;
    private String vehicle_class;
    private Double price;
    private Integer mileage;

    //Datum od kada je moguce iznajmiti vozilo
    private String startDate;

    //Datum do kada je moguce iznajmiti vozilo
    private String endDate;

    //Odnosi se na to da li automobil ima ogranicenu kilometrazu koju moze da predje prilikom rentanja
    private Boolean limited_rent_mileage;

    //ukoliko je limited_rent_mileage true tj ako je kilometraza ogranicena allowed mileage predstavlja koja je gornja granica
    private Integer allowed_mileage;

    //Polje koje oznacava da li je moguca kupovina protekcije za smanjenje troskova u slucaju nezgode
    private Boolean collision_damage_waiver;

    private Integer child_seat;


}
