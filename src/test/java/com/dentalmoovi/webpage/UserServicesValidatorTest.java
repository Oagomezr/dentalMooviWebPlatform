package com.dentalmoovi.webpage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dentalmoovi.webpage.dtos.UserDTO;
import com.dentalmoovi.webpage.services.UserSer;

@SpringBootTest
class UserServicesValidatorTest {

    @Autowired
    private UserSer userSer;

    @Test
    public void userServicesValidatorTest(){ 
        //create test
        UserDTO userSend = new UserDTO(null, "Oagomezr", "Oscar Alberto", "Gomez Rodriguez", "Oscar.gomez.ro17@gmail.com", "3202148944", null, "Masculino", "Growth31053017", null);
        UserDTO userReveive = userSer.createUser(userSend);
        assertNotNull(userReveive);
        assertEquals(1, userReveive.getRoles().size());
    }
}
