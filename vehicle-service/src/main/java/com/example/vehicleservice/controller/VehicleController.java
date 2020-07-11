package com.example.vehicleservice.controller;

import com.example.vehicleservice.dto.ShowVehicleDTO;
import com.example.vehicleservice.dto.VehicleDTO;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;
import com.example.vehicleservice.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;
    
    @Autowired
    VehicleRepository vehicleRepo;

    Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @GetMapping()
    public ResponseEntity<Set<ShowVehicleDTO>> showVehicles(){
        Set<Vehicle> vehicles = vehicleService.showVehicles();
        Set<ShowVehicleDTO> return_vehicles = new HashSet<>();
        for (Vehicle v: vehicles){
            return_vehicles.add(new ShowVehicleDTO(v));
        }
        return new ResponseEntity<>(return_vehicles, HttpStatus.OK);
    }

    @GetMapping("/{id1}")
    public ResponseEntity<VehicleDTO> getVehicleDetails(@PathVariable("id1") String id1, @RequestHeader("Username") String username){
        logger.info("Korisnik {} zatrazio detalje za vozilo sa id-jem {}. Vrijeme: Date: {}",username,id1,LocalDateTime.now());
        try{
            return vehicleService.getVehicleDetails(Long.parseLong(id1));
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Vozilo sa id-jem {} nije pronadjeno {}", id1, LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDTO vehicle, @RequestHeader ("Username") String username ){
        logger.info("Korisnik {} zatrazio kreiranje vozila marke {}. Vrijeme {}", username, vehicle.getModel(), LocalDateTime.now());
        return (ResponseEntity<String>) vehicleService.createVehicle(vehicle, username);

    }

    @GetMapping("/user")
    public ResponseEntity<Set<VehicleDTO>> getMyCars(@RequestHeader(value = "Username") String username){
    	boolean success = vehicleService.havePermission(username);
    	if(!success) {
    		return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
    	}
        logger.info("Korisnik {} zatrazio prikaz svojih vozila. Vrijeme ", username, LocalDateTime.now());
        return vehicleService.getMyCars(username);
    }
    
    @GetMapping("/addDiscountToCar/{id}/{discount}/{howmany}")
    public ResponseEntity<String> addDiscountToCar(@PathVariable("id") Long id,@PathVariable("discount") Long discount,@PathVariable("howmany") Long howmany){
    	return vehicleService.addDiscountToCar(id,discount,howmany);
    }
    
    @GetMapping("/getDiscountByVehicle/{id}")
    public ResponseEntity<String> getDiscountByVehicle(@PathVariable("id") Long id){
    	Vehicle vehicle = null;
    	List<Vehicle> vehicles = vehicleRepo.findAll();
    	for(Vehicle v : vehicles) {
    		if(v.getId() == id) {
    			vehicle = v;
    		}
    	}
    	if(vehicle == null) {
    		return new ResponseEntity<String>("",HttpStatus.OK);
    	}
    	String result = vehicle.getDiscount()+","+vehicle.getDiscountDays()+","+vehicle.getPrice();
    	return new ResponseEntity<String>(result,HttpStatus.OK);
    }

    @GetMapping("/getMostKmCar")
    public ResponseEntity<VehicleDTO> getMostKmCar(@RequestHeader(value = "Username") String username){
    	return vehicleService.getMostKmCar(username);
    }
    
    @GetMapping("/getMostPriceCar")
    public ResponseEntity<VehicleDTO> getMostPriceCar(@RequestHeader(value = "Username") String username){
    	return vehicleService.getMostPriceCar(username);
    }
    
    @GetMapping("/updateKm/{id}/{km}")
    public ResponseEntity<Boolean> updateKm(@PathVariable("id") Long id,@PathVariable("km") Integer km){
    	return vehicleService.updateKm(id,km);
    }
}
