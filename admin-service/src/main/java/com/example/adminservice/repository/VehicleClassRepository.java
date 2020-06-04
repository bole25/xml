package com.example.adminservice.repository;

import com.example.adminservice.model.VehicleClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleClassRepository extends JpaRepository<VehicleClass, Long> {
    void deleteByName(String name);
}
