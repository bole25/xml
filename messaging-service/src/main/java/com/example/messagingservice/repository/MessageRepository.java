package com.example.messagingservice.repository;

import com.example.messagingservice.model.Message;
import com.example.messagingservice.model.UserMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM messaging_db.message WHERE sender_username = ?1 ORDER BY time DESC ", nativeQuery = true)
    Set<Message> getSentMessages(String username);

    @Query(value = "SELECT * FROM messaging_db.message WHERE receiver_username = ?1 ORDER BY time DESC ", nativeQuery = true)
    Set<Message> getReceivedMessages(String username);
}
