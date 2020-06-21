package com.example.messagingservice.repository;

import com.example.messagingservice.model.UserMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessagesRepository extends JpaRepository<UserMessages, Long> {

    @Query(value = "SELECT u FROM UserMessages u WHERE u.username = 1", nativeQuery = true)
    UserMessages getUserMessagesByUsername(String username);
}
