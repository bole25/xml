package com.example.loginservice.dto;

import com.example.loginservice.enumeration.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResetPasswordDTO {
    private String username;

    private String password;    
    
}
