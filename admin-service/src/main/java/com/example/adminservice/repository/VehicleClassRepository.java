package com.example.adminservice.repository;

import com.example.adminservice.model.Brand;
import com.example.adminservice.model.VehicleClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VehicleClassRepository extends JpaRepository<VehicleClass, Long> {
    void deleteByName(String name);

    @Query(value = "select * from vehicle_class", nativeQuery = true)
    Set<VehicleClass> getAll();
}
