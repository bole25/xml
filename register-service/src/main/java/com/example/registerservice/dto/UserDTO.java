package com.example.registerservice.dto;

import java.time.LocalTime;

import com.example.registerservice.enumeration.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;

    private String password;

    private RoleEnum role;
    
    private Boolean active;

	
    
    
}
