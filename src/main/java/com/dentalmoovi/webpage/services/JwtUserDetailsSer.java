package com.dentalmoovi.webpage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.dentalmoovi.webpage.exceptions.DataNotFoundException;
import com.dentalmoovi.webpage.models.Roles;
import com.dentalmoovi.webpage.models.Users;
import com.dentalmoovi.webpage.repositories.IUsersRep;

public class JwtUserDetailsSer implements UserDetailsService{
    @Autowired
    private IUsersRep usersRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws DataNotFoundException {
        
        Users user = usersRep.findByUsername(username).orElseThrow(() -> new DataNotFoundException("User Not Found"));
        
        return User
                .withUsername(username)
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Roles::getNameRole).toArray(String[]::new))
                .build();
    }

}