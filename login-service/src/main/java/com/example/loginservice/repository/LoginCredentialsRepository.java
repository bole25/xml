package com.example.loginservice.repository;

import com.example.loginservice.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    @Query(value = "SELECT * FROM authentication.user_credentials WHERE username=?1" , nativeQuery = true)
    UserCredentials findByUsername(String username);
}
