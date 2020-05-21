package com.example.messagingservice.model;

public class Customer {
    private Long id;

	private String name;
	
	private String surname;
	
	private Integer jmbg;

	public Customer() {
		super();
	}
	public Customer(String name, String surname, Integer jmbg) {
		super();
		this.name = name;
		this.surname = surname;
		this.jmbg = jmbg;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Integer getJmbg() {
		return jmbg;
	}
	public void setJmbg(Integer jmbg) {
		this.jmbg = jmbg;
	}
}
