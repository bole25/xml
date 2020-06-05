package com.example.loginservice.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.loginservice.model.UserCredentials;

public interface ManageUsersPermissionsRepository extends JpaRepository<UserCredentials, Long>{

	@Query(value = "SELECT * FROM authentication.user_credentials WHERE username=?1" , nativeQuery = true)
    UserCredentials findByUsername(String username);
	
	@Query(value = "SELECT * FROM authentication.user_credentials WHERE active=?1" , nativeQuery = true)
    Set<UserCredentials> getAllByActivationCondition(Boolean isActivate);
}
