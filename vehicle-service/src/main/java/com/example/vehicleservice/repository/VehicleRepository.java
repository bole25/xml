package com.example.vehicleservice.repository;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vehicleservice.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value="select * from vehicle where ?1 between start_date " +
            "and end_date and  ?2 between start_date " +
            "and end_date ", nativeQuery = true)
    Set<Vehicle> searchVehicle(Date start, Date end);

    @Query(value = "select * from vehicle", nativeQuery = true)
    Set<Vehicle> showVehicles();

    @Query(value = "select * from vehicle where id = ?1", nativeQuery = true)
    Vehicle getDetails(Long id);
    
    @Query(value = "select * from vehicle where company_username = ?1",nativeQuery = true)
    Set<Vehicle> showVehiclesByCompanyUsername(String username);

}
