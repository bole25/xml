package com.example.vehicleservice.service;

import com.example.vehicleservice.dto.SearchByCompanyUsernameDTO;
import com.example.vehicleservice.dto.ShowVehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicleservice.dto.VehicleDTO;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

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
            }
            return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> createVehicle(VehicleDTO vehicle){
        try {
            Vehicle v = new Vehicle(vehicle);
            Vehicle v1 = vehicleRepository.save(v);
            String s = Long.toString(v1.getId())+".txt";
            makeDir(s, vehicle.getImages());
            return new ResponseEntity<>("", HttpStatus.OK);
            } catch (Exception ex){
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
            return new ResponseEntity<>(retSet, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
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

}
