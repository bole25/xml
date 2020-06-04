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
        this.companyUsername = vehicleDTO.getCompanyUsername();

        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter1.parse(vehicleDTO.getStartDate());
        Date endDate = formatter1.parse(vehicleDTO.getEndDate());
        this.startDate = startDate;
        this.endDate = endDate;
    }


	public Long getId() {
		return id;
	}

	public String getCompanyUsername() {
		return companyUsername;
	}


	public void setCompanyUsername(String companyUsername) {
		this.companyUsername = companyUsername;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getFuel_type() {
		return fuel_type;
	}


	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}


	public String getTransmission() {
		return transmission;
	}


	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}


	public String getVehicle_class() {
		return vehicle_class;
	}


	public void setVehicle_class(String vehicle_class) {
		this.vehicle_class = vehicle_class;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Integer getMileage() {
		return mileage;
	}


	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Boolean getLimited_rent_mileage() {
		return limited_rent_mileage;
	}


	public void setLimited_rent_mileage(Boolean limited_rent_mileage) {
		this.limited_rent_mileage = limited_rent_mileage;
	}


	public Integer getAllowed_mileage() {
		return allowed_mileage;
	}


	public void setAllowed_mileage(Integer allowed_mileage) {
		this.allowed_mileage = allowed_mileage;
	}


	public Boolean getCollision_damage_waiver() {
		return collision_damage_waiver;
	}


	public void setCollision_damage_waiver(Boolean collision_damage_waiver) {
		this.collision_damage_waiver = collision_damage_waiver;
	}


	public Integer getChild_seat() {
		return child_seat;
	}


	public void setChild_seat(Integer child_seat) {
		this.child_seat = child_seat;
	}


	public Set<Occupation> getOccupations() {
		return occupations;
	}


	public void setOccupations(Set<Occupation> occupations) {
		this.occupations = occupations;
	}
    
}
