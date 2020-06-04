package com.example.adminservice.repository;

import com.example.adminservice.model.Brand;
import com.example.adminservice.model.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransmissionRepository extends JpaRepository<Transmission, Long> {

    void deleteByName(String name);

    @Query(value = "select * from transmission", nativeQuery = true)
    Set<Transmission> getAll();
}
