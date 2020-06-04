package com.example.vehicleservice.model;

import com.example.vehicleservice.dto.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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

    private String brand;
    private String model;
    private String fuel_type;
    private String transmission;
    private String vehicle_class;
    private Double price;
    private Integer mileage;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Occupation> occupations;


    public Vehicle(VehicleDTO vehicleDTO) throws ParseException {
        this.allowed_mileage = vehicleDTO.getAllowed_mileage();
        this.brand = vehicleDTO.getBrand();
        this.child_seat = vehicleDTO.getChild_seat();
        this.limited_rent_mileage = vehicleDTO.getLimited_rent_mileage();
        this.occupations = new HashSet<>();
        this.collision_damage_waiver = vehicleDTO.getCollision_damage_waiver();
        this.fuel_type = vehicleDTO.getFuel_type();
        this.mileage = vehicleDTO.getMileage();
        this.model = vehicleDTO.getModel();
        this.price = vehicleDTO.getPrice();
        this.transmission = vehicleDTO.getTransmission();
        this.vehicle_class = vehicleDTO.getVehicle_class();

        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter1.parse(vehicleDTO.getStartDate());
        Date endDate = formatter1.parse(vehicleDTO.getEndDate());
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
