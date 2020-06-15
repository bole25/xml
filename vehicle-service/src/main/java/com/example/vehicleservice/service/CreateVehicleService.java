package com.example.vehicleservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicleservice.dto.VehicleDTO;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Service
public class CreateVehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseEntity<String> createVehicle(VehicleDTO vehicle){
        try {
            Vehicle v = new Vehicle(vehicle);
            Vehicle v1 = vehicleRepository.save(v);
            String s = Long.toString(v1.getId())+".txt";
            makeDir(s, vehicle.getImages());
            return new ResponseEntity<>("Vehicle created", HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
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
