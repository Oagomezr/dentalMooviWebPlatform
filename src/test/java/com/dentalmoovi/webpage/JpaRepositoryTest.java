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

import com.dentalmoovi.webpage.models.entities.Roles;
import com.dentalmoovi.webpage.models.entities.Users;
import com.dentalmoovi.webpage.repositories.IRolesRep;
import com.dentalmoovi.webpage.repositories.IUsersRep;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class JpaRepositoryTest {
    
    @Autowired
    private IUsersRep usersRep;

    @Autowired
    private IRolesRep rolesRep;

    @BeforeEach
    public void setup(){
        Roles user = new Roles();
        user.setNameRole("USER");
        Roles admin = new Roles();
        admin.setNameRole("ADMIN");
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
        Users user1 = new Users();
        user1.setFirstName("uno");
        user1.setLastName("usuario1");
        user1.setEmail("usuario1@mail.com");
        user1.setCelPhone("0123456789");
        user1.setGender("Male");
        user1.setPassword("password");
        Users user2 = new Users();
        user2.setFirstName("dos");
        user2.setLastName("usuario2");
        user2.setEmail("usuario2@mail.com");
        user2.setCelPhone("0123456789");
        user1.setRoles(normalRoles);
        user2.setRoles(normalRoles);
        user2.setGender("Female");
        user2.setPassword("password");

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
