package com.example.loginservice.model;

import com.example.loginservice.enumeration.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCredentials {

    private String username;

    private String password;

    private RoleEnum role;

}
