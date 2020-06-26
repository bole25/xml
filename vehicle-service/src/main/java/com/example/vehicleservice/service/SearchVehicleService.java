package com.example.vehicleservice.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
