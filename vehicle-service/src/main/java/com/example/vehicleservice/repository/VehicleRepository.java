package com.example.vehicleservice.repository;

import com.example.vehicleservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value="select * from vehicle where ?1 between start_date " +
            "and end_date and  ?2 between start_date " +
            "and end_date ", nativeQuery = true)
    Set<Vehicle> searchVehicle(Date start, Date end);
}
