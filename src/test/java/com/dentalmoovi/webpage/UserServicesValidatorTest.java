package com.dentalmoovi.webpage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dentalmoovi.webpage.dtos.UserDTO;
import com.dentalmoovi.webpage.exceptions.DataNotFoundException;
import com.dentalmoovi.webpage.services.UserSer;

@SpringBootTest
class UserServicesValidatorTest {

    @Autowired
    private UserSer userSer;

    private UserDTO userReceive;

    @BeforeEach
    void init(){
        try {
            userSer.getUserById(1L);
        } catch (Exception e) {
            UserDTO userSend = new UserDTO(null, "Oagomezr", "Oscar Alberto", "Gomez Rodriguez", "Oscar.gomez.ro17@gmail.com", "3202148944", null, "Masculino", "Growth31053017", null);
            userReceive = userSer.createUser(userSend);
        }
    }

    @Test
    void createUserValidator(){
        assertNotNull(userReceive);
        assertEquals(1, userReceive.getRoles().size());
    }

    @Test
    void getUserAndUpdateValidator(){
        UserDTO getUser = userSer.getUserById(1L);
        assertNotNull(getUser);
        assertEquals(1, getUser.getRoles().size());
        UserDTO userSend2 = new UserDTO(null, "Aigomezr", "Angie Natalia", "Gomez Rodriguez", "Angie.natalia47@gmail.com", "3222148944", null, "Femenino", "PerrosEnCorto", null);
        UserDTO userUpdate = userSer.updateUser(1L, userSend2);
        assertEquals(userSend2.getUsername(), userUpdate.getUsername());
    }

    @Test
    void deleteUserValidator(){
        assertDoesNotThrow(() -> userSer.deleteUser(1L));
        assertThrows(DataNotFoundException.class, () -> userSer.getUserById(1L));
    }

}
