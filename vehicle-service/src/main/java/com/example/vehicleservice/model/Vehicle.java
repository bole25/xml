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

    @Column()
    private String brand;

	@Column()
    private String model;

	@Column()
	private String fuel_type;

	@Column()
	private String transmission;

	@Column()
	private String vehicle_class;

	@Column()
	private Double price;

	@Column()
	private Integer mileage;


    private String companyUsername;

    //Datum od kada je moguce iznajmiti vozilo
	@Column()
    private Date startDate;

    //Datum do kada je moguce iznajmiti vozilo
	@Column()
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
    
    public Vehicle(String brand, String model, Double price, String companyUsername, Date startDate, Date endDate) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.companyUsername = companyUsername;
		this.startDate = startDate;
		this.endDate = endDate;
		this.occupations = new HashSet<Occupation>();
	}


	public Vehicle(VehicleDTO vehicleDTO) throws ParseException {
        this.allowed_mileage = vehicleDTO.getAllowedMileage();
        this.brand = vehicleDTO.getBrand();
        this.child_seat = vehicleDTO.getChildSeat();
        this.limited_rent_mileage = vehicleDTO.getLimitedRentMileage();
        this.occupations = new HashSet<>();
        this.collision_damage_waiver = vehicleDTO.getCollisionDamageWaiver();
        this.fuel_type = vehicleDTO.getFuelType();
        this.mileage = vehicleDTO.getMileage();
        this.model = vehicleDTO.getModel();
        this.price = vehicleDTO.getPrice();
        this.transmission = vehicleDTO.getTransmission();
        this.vehicle_class = vehicleDTO.getVehicleClass();
        this.companyUsername = vehicleDTO.getCompanyUsername();

        this.startDate = vehicleDTO.getStartDate();
        this.endDate = vehicleDTO.getEndDate();
    }

}
