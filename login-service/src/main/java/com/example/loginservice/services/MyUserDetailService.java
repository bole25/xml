package com.example.loginservice.services;

import com.example.loginservice.model.UserCredentials;
import com.example.loginservice.repository.LoginCredentialsRepository;
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

    @Autowired
    LoginCredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCredentials user= credentialsRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username + "not found");
        }

        List<GrantedAuthority> authorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(user.getRole().name());
        return new User(username, encoder.encode(user.getPassword()), authorities);

    }
}
