package com.example.requestservice.repository;

import com.example.requestservice.model.UserRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestsRepository extends JpaRepository<UserRequests, Long> {

    @Query(value = "select * from user_requests where username = ?1", nativeQuery = true)
    UserRequests getRequestsByUsername(String username);
}
