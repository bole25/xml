package com.example.vehicleservice.service;


import com.example.vehicleservice.controller.VehicleController;
import com.example.vehicleservice.dto.SearchByCompanyUsernameDTO;
import com.example.vehicleservice.dto.ShowVehicleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehicleservice.dto.VehicleDTO;
import com.example.vehicleservice.feignclient.LoginClient;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    
    @Autowired
    LoginClient loginClient;

    Logger logger = LoggerFactory.getLogger(VehicleService.class);

    public Set<Vehicle> showVehicles() {
        return vehicleRepository.showVehicles();
    }

    public ResponseEntity<VehicleDTO> getVehicleDetails(Long id){
        try {
            Vehicle v = vehicleRepository.getDetails(id);
            VehicleDTO vehicleDTO = new VehicleDTO(v);
            try {
                String s = "images/"+Long.toString(v.getId())+".txt";
                File myObj = new File(s);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    vehicleDTO.getImages().add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");

                e.printStackTrace();
                logger.warn("Fajl {} nije pronadjen {}", Long.toString(v.getId())+".txt", LocalDateTime.now());
            }
            logger.info("Detalji za vozilo sa id-jem {} vraceni {}", v.getId(), LocalDateTime.now());
            return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Vozilo sad id-jem {} nije pronadjeno {}",id,LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> createVehicle(VehicleDTO vehicle, String username){
        if(vehicleRepository.numberOfVehicles(username) > 2){
            return new ResponseEntity<>("Ne moze se kreirati vise od 3 vozila", HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            vehicle.setCompanyUsername(username);
            Vehicle v = new Vehicle(vehicle);
            Vehicle v1 = vehicleRepository.save(v);
            String s = Long.toString(v1.getId())+".txt";
            makeDir(s, vehicle.getImages());
            logger.info("Korisnik {} uspjesno kreirao vozilo {} sa id-jem {}. {}", username, v.getBrand(), v1.getId(), LocalDateTime.now());
            return new ResponseEntity<>(v1, HttpStatus.OK);
            } catch (Exception ex){
               logger.error("Neuspjesno kreiranje vozila {} zatrazeno od korisnika {}. {}", vehicle.getBrand(),username, LocalDateTime.now() );
                ex.printStackTrace();
                return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
            }
    }

    public ResponseEntity<Set<ShowVehicleDTO>> GetVehiclesByDateAndCompanyUsername(SearchByCompanyUsernameDTO search) {

        Set<ShowVehicleDTO> vehiclesDTO = new HashSet<ShowVehicleDTO>();
        Set<Vehicle> vehicles = vehicleRepository.searchVehicle(search.getFrom(), search.getTo());
        for(Vehicle v : vehicles) {
            if(v.getCompanyUsername().equals(search.getUsername())) {
                vehiclesDTO.add(new ShowVehicleDTO(v));
            }
        }
        return new ResponseEntity<>(vehiclesDTO,HttpStatus.OK);
    }


    public ResponseEntity<Set<VehicleDTO>> getMyCars(String username){
        try {
            Set<Vehicle> vehicles = vehicleRepository.showVehiclesByCompanyUsername(username);
            Set<VehicleDTO> retSet = new HashSet<>();
            for(Vehicle v : vehicles){
                VehicleDTO vehicleDTO = new VehicleDTO(v);
                retSet.add(vehicleDTO);
            }
            logger.info("Korisnik {} dobio svoja vozila. {}", username, LocalDateTime.now());
            return new ResponseEntity<>(retSet, HttpStatus.OK);
        }
        catch (Exception e){

            logger.error("Neuspjesno dobavljanje vozila za korisnika {}. {}", username, LocalDateTime.now());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    private void makeDir(String path, Set<String> images){
    
        try {
            System.out.println(System.getProperty("user.dir"));
            if(Files.notExists(Paths.get("images"))){
                System.out.println("ne postoji");
                File dir = new File("images");
                dir.mkdirs();
            }
            File myObj = new File("images/"+path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("images/"+path);
            for(String s : images){
                myWriter.write(s+"\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    @Transactional
	public boolean havePermission(String username) {
    	Boolean connected = loginClient.checkPerm(username,"1");
		return connected;
	}

	public ResponseEntity<String> addDiscountToCar(Long id, Long discount, Long howmany) {
		Vehicle vehicle = vehicleRepository.getOne(id);
		if(vehicle == null) {
			return new ResponseEntity<String>("",HttpStatus.BAD_REQUEST);
		}
		vehicle.setDiscount(Integer.parseInt(discount.toString()));
		vehicle.setDiscountDays(Integer.parseInt(howmany.toString()));
		vehicle = vehicleRepository.save(vehicle);
		
		return new ResponseEntity<String>("",HttpStatus.OK);
	}

	public ResponseEntity<VehicleDTO> getMostKmCar() {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		if(vehicles == null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		Vehicle best = vehicles.get(0);
		for(Vehicle v : vehicles) {
			if(v.getMileage() > best.getMileage()) {
				best = v;
			}
		}
		VehicleDTO vDTO = new VehicleDTO();
		vDTO.setId(best.getId());
		vDTO.setBrand(best.getBrand());
		vDTO.setModel(best.getModel());
		return new ResponseEntity<VehicleDTO>(vDTO,HttpStatus.OK);
	}

	public ResponseEntity<VehicleDTO> getMostPriceCar() {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		if(vehicles == null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		Vehicle best = vehicles.get(0);
		for(Vehicle v : vehicles) {
			if(v.getPrice() > best.getPrice()) {
				best = v;
			}
		}
		VehicleDTO vDTO = new VehicleDTO();
		vDTO.setId(best.getId());
		vDTO.setBrand(best.getBrand());
		vDTO.setModel(best.getModel());
		return new ResponseEntity<VehicleDTO>(vDTO,HttpStatus.OK);
	}
}
