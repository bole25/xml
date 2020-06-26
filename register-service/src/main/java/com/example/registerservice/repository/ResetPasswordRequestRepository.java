package com.example.registerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.registerservice.model.ResetPasswordRequest;


public interface ResetPasswordRequestRepository extends JpaRepository<ResetPasswordRequest, Long>{
    @Query(value = "select * from reset_password_request where username = ?1", nativeQuery = true)
    ResetPasswordRequest getRequestByUsername(String username);
}

