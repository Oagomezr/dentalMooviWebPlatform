package com.dentalmoovi.webpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dentalmoovi.webpage.models.Roles;
import com.dentalmoovi.webpage.repositories.IRolesRep;

import jakarta.annotation.PostConstruct;

@Component
public class StartupInitializer {

    @Autowired
    private IRolesRep rolesRep;
    
    @PostConstruct
    public void init(){

        Roles user = new Roles(null, "USER", null);
        Roles admin = new Roles(null, "ADMIN", null);
        rolesRep.save(user);
        rolesRep.save(admin);
    }
}
