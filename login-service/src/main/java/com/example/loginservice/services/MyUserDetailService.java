package com.example.loginservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("korisnik")){
            List<GrantedAuthority> authorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_USER");
            return new User(username, encoder.encode("korisniksam"), authorities);

        }else if(username.equals("administrator")){

            List<GrantedAuthority> authorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_ADMIN");
            return new User(username, encoder.encode("adminsam"), authorities);

        }

        throw new UsernameNotFoundException(username + "not found");
    }
}
