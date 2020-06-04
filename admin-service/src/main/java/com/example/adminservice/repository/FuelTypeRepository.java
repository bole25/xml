package com.example.adminservice.repository;

import com.example.adminservice.model.Brand;
import com.example.adminservice.model.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    void deleteByName(String name);

    @Query(value = "select * from fuel_type", nativeQuery = true)
    Set<FuelType> getAll();
}
