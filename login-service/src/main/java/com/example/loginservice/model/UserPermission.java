package com.example.loginservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.example.loginservice.enumeration.RoleEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserPermission {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 	@Column(nullable = false)
	    private Boolean vehiclePerm;
	 	
	 	@Column(nullable = false)
	    private Boolean requestPerm;
	 	
	 	@Column(nullable = false)
	    private Boolean otherPerm;
	
	 	@OneToOne
	 	private UserCredentials user;
	 	
}
