package com.example.vehicleservice.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.vehicleservice.controller.SearchVehicleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicleservice.dto.SearchDTO;
import com.example.vehicleservice.model.Occupation;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;

@Service
public class SearchVehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    Logger logger = LoggerFactory.getLogger(SearchVehicleService.class);

    public ResponseEntity<Set<Vehicle>> searchVehicle(SearchDTO searchDTO){
        Set<Vehicle> vehicles = new HashSet<>();
        Set<Vehicle> retVehicles = new HashSet<>();

        try {
            Date startDate = searchDTO.getStartDate();
            Date endDate = searchDTO.getEndDate();
            if(startDate.after(endDate)){
                logger.error("U pretrazi je pocetni datum posle krajnjeg. Pocetni datum: {} Krajnji datum: {}. {}", searchDTO.getStartDate(), searchDTO.getEndDate(), LocalDateTime.now());
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            vehicles = vehicleRepository.searchVehicle(startDate, endDate);
            Boolean work = Boolean.TRUE;
            for(Vehicle v: vehicles){
                work = Boolean.TRUE;
                for(Occupation o: v.getOccupations()){
                    if(!compareDates(startDate,endDate,o.getStartDate(),o.getEndDate())){
                        work = Boolean.FALSE;
                        break;
                    }
                }
                if(work){
                    retVehicles.add(v);
                }
            }
            logger.info("Uspjesna pretraga. {}", LocalDateTime.now());
            return new ResponseEntity<>(retVehicles, HttpStatus.OK);
        }
        catch (Exception ex) {
            logger.error("Neuspjesna pretraga. {}", LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public Boolean compareDates(Date startDate, Date endDate, Date startCompare, Date endCompare){
        Boolean ret = Boolean.FALSE;
        if(startDate.after(endCompare)){
            ret = Boolean.TRUE;
        }
        if(endDate.before(startCompare)){
            ret = Boolean.TRUE;
        }
        return ret;
    }
    
    
    public ResponseEntity<Set<Vehicle>> getAllVehicles(){
        Set<Vehicle> vehicles = new HashSet<>();
        vehicles = vehicleRepository.showVehicles();       
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

	public ResponseEntity<Set<Vehicle>> advancedSearchVehicle(SearchDTO searchDTO) {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		Set<Vehicle> retVehicles = new HashSet<Vehicle>();
		for(Vehicle v : vehicles) {
			Boolean brandCheck = true;
			Boolean modelCheck = true;
			Boolean gasCheck = true;
			Boolean transCheck = true;
			Boolean classCheck = true;
			Boolean priceCheck = true;
			Boolean kmAmountCheck = true;
			Boolean plannedKmAmountCheck = true;
			Boolean cdwCheck = true;
			Boolean kidsCheck = true;
			
			if(searchDTO.getKidsSeats() != -1){
				if(v.getChild_seat() == null || v.getChild_seat() == searchDTO.getKidsSeats()){
					kidsCheck = true;

				}else {
					kidsCheck = false;
				}
			}else {
				kidsCheck = true;
			}
			
			if(searchDTO.getPlannedKMAmount() != -1){
				if(v.getMileage() == null || v.getMileage() == searchDTO.getPlannedKMAmount()){
					plannedKmAmountCheck = true;

				}else {
					plannedKmAmountCheck = false;
				}
			}else {
				plannedKmAmountCheck = true;

			}
			
			if(searchDTO.getKMAmount() != -1){
				if(v.getAllowed_mileage() == null || v.getAllowed_mileage() == searchDTO.getKMAmount()){
					kmAmountCheck = true;

				}else {
					kmAmountCheck = false;
				}
			}else {
				kmAmountCheck = true;

			}
			
			if(searchDTO.getTransmission() != null){
				if(v.getTransmission() == null || v.getTransmission().contains(searchDTO.getTransmission())){
					transCheck = true;

				}else {
					transCheck = false;
				}
			}else {
				transCheck = true;

			}
			
			if(searchDTO.getGas() != null){
				if(v.getFuel_type() == null || v.getFuel_type().contains(searchDTO.getGas())){
					gasCheck = true;

				}else {
					gasCheck = false;
				}
			}else {
				gasCheck = true;

			}
			
			if(searchDTO.getModel() != null){
				if(v.getModel() == null || v.getModel().contains(searchDTO.getModel())){
					modelCheck = true;

				}else {
					modelCheck = false;
				}
			}else {
				modelCheck = true;

			}
			
			if(searchDTO.getPrice1() != -1 && searchDTO.getPrice2() != -1) {
				if(v.getPrice() >= searchDTO.getPrice1() && v.getPrice() <= searchDTO.getPrice2()){
					priceCheck = true;

				}else {
					priceCheck = false;
				}
			}else {
				priceCheck = true;
			}
			
			if(searchDTO.getCDW() != null){
				if(v.getCollision_damage_waiver() == null || v.getCollision_damage_waiver() == searchDTO.getCDW()){
					cdwCheck = true;

				}else {
					cdwCheck = false;
				}
			}else {
				cdwCheck = true;

			}
			
			if(searchDTO.getBrand() != null){
				if(v.getBrand() == null || v.getBrand().contains(searchDTO.getBrand())){
					brandCheck = true;

				}else {
					brandCheck = false;
				}
			}else {
				brandCheck = true;

			}
			
			if(brandCheck && cdwCheck && priceCheck && modelCheck && gasCheck && transCheck && modelCheck && kmAmountCheck && plannedKmAmountCheck && kidsCheck && classCheck){
				retVehicles.add(v);
			}
			
		}
		
		
		 return new ResponseEntity<>(retVehicles, HttpStatus.OK);
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
