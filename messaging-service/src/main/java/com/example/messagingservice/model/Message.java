package com.example.messagingservice.model;

import com.example.messagingservice.dto.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String textMessage;

	@Column(nullable = false)
	private String sender_username;

	@Column(nullable = false)
	private String receiver_username;

	@Column(nullable = false)
	private LocalDateTime time;

    public Message(String sender, MessageDTO messageDTO) {
    	this.sender_username = sender;
    	this.textMessage = messageDTO.getText();
    	this.receiver_username = messageDTO.getReceiver();
    	this.time = LocalDateTime.now();
    }
}
