package com.example.registerservice.model;

import java.time.LocalTime;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.registerservice.enumeration.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegistrationRequest {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(unique = true, nullable = false)
	private String username;
    @Column
	private String password;
    @Column
	private RoleEnum role;
    @Column
	private Boolean active;
    
    // data for validation
    @Column
    private int validationCode;
    @Column
    private LocalTime validationTime;

	
	
}
