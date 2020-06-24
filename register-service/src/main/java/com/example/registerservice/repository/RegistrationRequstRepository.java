package com.example.registerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.registerservice.model.RegistrationRequest;
import com.example.registerservice.model.Validation;

@Repository
public interface RegistrationRequstRepository extends JpaRepository<RegistrationRequest, Long> {

    @Query(value = "select * from registration_request where username = ?1", nativeQuery = true)
    RegistrationRequest getRequestByUsername(String username);
    
}
