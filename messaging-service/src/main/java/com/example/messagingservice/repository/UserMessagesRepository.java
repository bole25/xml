package com.example.messagingservice.repository;

import com.example.messagingservice.model.UserMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessagesRepository extends JpaRepository<UserMessages, Long> {

    @Query(value = "SELECT u FROM UserMessages u WHERE u.username = ?1")
    UserMessages getUserMessagesByUsername(String username);

    @Modifying
    @Query(value = "INSERT INTO user_messages_received (`user_messages_id`, `received_id`) VALUES (?1,?2)", nativeQuery = true)
    void addReceivedMessages(Long userId, Long messageId);

    @Modifying
    @Query(value = "INSERT INTO user_messages_sent (`user_messages_id`, `sent_id`) VALUES (?1,?2)", nativeQuery = true)
    void addSentMessages(Long userId, Long messageId);
}
