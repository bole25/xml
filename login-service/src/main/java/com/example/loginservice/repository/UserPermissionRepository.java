package com.example.loginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.loginservice.model.UserPermission;

@Repository
public interface UserPermissionRepository  extends JpaRepository<UserPermission, Long>{

	@Query(value = "SELECT * FROM authentication.user_permission WHERE user_id=?1" , nativeQuery = true)
    UserPermission findByUserId(Long id);
	
}
