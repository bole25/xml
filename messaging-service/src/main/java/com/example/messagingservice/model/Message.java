package com.example.messagingservice.model;

import com.example.messagingservice.adapter.LocalDateAdapter;
import com.example.messagingservice.dto.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", namespace = "http://localhost:8088/messages")
@XmlRootElement(name = "messageClass")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	private Long id;

	@Column(nullable = false)
	@XmlElement
	private String textMessage;

	@Column(nullable = false)
	@XmlElement
	private String sender_username;

	@Column(nullable = false)
	@XmlElement
	private String receiver_username;

	@Column(nullable = false)
	@XmlElement
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	private LocalDateTime time;

    public Message(String sender, MessageDTO messageDTO) {
    	this.sender_username = sender;
    	this.textMessage = messageDTO.getText();
    	this.receiver_username = messageDTO.getReceiver();
    	this.time = LocalDateTime.now();
    }
}
