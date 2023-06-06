package com.dentalmoovi.webpage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dentalmoovi.webpage.models.Roles;
import com.dentalmoovi.webpage.models.Users;
import com.dentalmoovi.webpage.repositories.IRolesRep;
import com.dentalmoovi.webpage.repositories.IUsersRep;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JpaRepositoryTest {
    
    @Autowired
    private IUsersRep usersRep;

    @Autowired
    private IRolesRep rolesRep;

    @BeforeEach
    public void setup(){
        Roles user = new Roles(null, "USER", null);
        Roles admin = new Roles(null, "ADMIN", null);
        rolesRep.save(user);
        rolesRep.save(admin);
    }

    @Test
    void createUsers() throws Exception{

        //define the USER role
        Roles defaultRole = rolesRep.findByNameRole("USER").orElseThrow(() -> new Exception("Not Found"));
        Set<Roles> normalRoles = new HashSet<>();
        normalRoles.add(defaultRole);

        //create 2 users to do test
        Users user1 = new Users(null, "Usuario1", "uno", "usuario1", "usuario1@mail.com", "0123456789", null, "indefinido", "12345", null);
        Users user2 = new Users(null, "Usuario2", "dos", "usuario2", "usuario2@mail.com", "0123456789", null, "indefinido", "12345", null);
        user1.setRoles(normalRoles);
        user2.setRoles(normalRoles);

        //save inside database
        usersRep.save(user1);
        usersRep.save(user2);

        //put in database immediately
        usersRep.flush();

        assertEquals(2, usersRep.findAll().size());

        Users user3 = usersRep.findById(1L).orElseThrow(() -> new Exception("NotFound"));
        //Set<Roles> rolesUser1 = user1.getRoles();

        assertEquals(1, user3.getRoles().size());
    }
}
