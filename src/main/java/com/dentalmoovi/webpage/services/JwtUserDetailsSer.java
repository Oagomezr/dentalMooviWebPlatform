package com.dentalmoovi.webpage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.dentalmoovi.webpage.exceptions.DataNotFoundException;
import com.dentalmoovi.webpage.models.entities.Roles;
import com.dentalmoovi.webpage.models.entities.Users;
import com.dentalmoovi.webpage.repositories.IUsersRep;

@Service
public class JwtUserDetailsSer implements UserDetailsService{
    @Autowired
    private IUsersRep usersRep;

    @Override
    public UserDetails loadUserByUsername(String email) throws DataNotFoundException {

        Users user = usersRep.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User Not Found"));
        
        return User
                .withUsername(email)
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Roles::getNameRole).toArray(String[]::new))
                .build();
    }

}