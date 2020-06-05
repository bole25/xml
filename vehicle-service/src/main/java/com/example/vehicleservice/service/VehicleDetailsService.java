package com.example.vehicleservice.service;

import com.example.vehicleservice.dto.VehicleDTO;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

@Service
public class VehicleDetailsService {

    @Autowired
    VehicleRepository vehicleRepository;

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

}
