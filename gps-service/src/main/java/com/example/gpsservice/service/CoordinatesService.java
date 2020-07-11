package com.example.gpsservice.service;

import com.example.gpsservice.model.Vehicle;
import com.example.gpsservice.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.RejectedExecutionException;

@Service
public class CoordinatesService {

    @Autowired
    private VehicleRepository vehicleRepository;

    private HashMap<Long, Vehicle> vehicles;

    public CoordinatesService(){
        this.vehicles = new HashMap<>();
    }
    public void updateCoordinates(Long vehicle_id, Double longitude, Double latitude){
        if (!vehicles.containsKey(vehicle_id)){
            Vehicle v = vehicleRepository.getOne(vehicle_id);
            if(v != null){
                vehicles.put(vehicle_id, new Vehicle());
            }else{
                return;
            }
        }
        vehicles.get(vehicle_id).setLongitude(longitude);
        vehicles.get(vehicle_id).setLatitude(latitude);
        vehicles.get(vehicle_id).setTime_stamp(LocalDateTime.now());
    }

    public Vehicle getCoordinates(Long vehicle_id){
        if(!vehicles.containsKey(vehicle_id)){
            throw new NullPointerException();
        }
        Vehicle v = vehicles.get(vehicle_id);
        if (v.getTime_stamp().isBefore(LocalDateTime.now().minusMinutes(1))){
            throw new RejectedExecutionException();
        }
        return v;
    }
}
