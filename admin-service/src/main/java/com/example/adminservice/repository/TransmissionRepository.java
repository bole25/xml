package com.example.adminservice.repository;

import com.example.adminservice.model.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmissionRepository extends JpaRepository<Transmission, Long> {

    void deleteByName(String name);
}
