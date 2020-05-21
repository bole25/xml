package com.example.messagingservice.model;

public class Message {

	private Long id;
	
	private String textMessage;
	private User sender;
	private User reciver;
	
	public Message() {
		super();
	}
	
	public Message(String textMessage, User sender, User reciver) {
		super();
		this.textMessage = textMessage;
		this.sender = sender;
		this.reciver = reciver;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReciver() {
		return reciver;
	}
	public void setReciver(User reciver) {
		this.reciver = reciver;
	}
	
	
}
