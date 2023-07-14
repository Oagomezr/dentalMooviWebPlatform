package com.dentalmoovi.webpage.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dentalmoovi.webpage.dtos.UserDTO;
import com.dentalmoovi.webpage.exceptions.DataNotFoundException;
import com.dentalmoovi.webpage.services.UserSer;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    
    @Autowired
    private UserSer userSer;

    @GetMapping("/admin")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List <UserDTO> users = userSer.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/public/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        try {
            UserDTO userCreated = userSer.createUser(userDTO);
            return ResponseEntity.created(URI.create("/users/"+userCreated.getIdUser())).body(userCreated);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        try {
            UserDTO userGetted = userSer.getUserById(id);
            return ResponseEntity.ok(userGetted);
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{token}")
    public ResponseEntity<UserDTO> getUserByToken(@PathVariable String token){
        try {
            UserDTO userGetted = userSer.getUserByJwt(token);
            return ResponseEntity.ok(userGetted);
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<UserDTO> actualizarUsuario(@PathVariable("id") Long id, @RequestBody UserDTO usersDTO) {
        try {
            UserDTO userUpdated = userSer.updateUser(id, usersDTO);
            return ResponseEntity.ok(userUpdated);
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try {
            userSer.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/{email}")
    public boolean checkEmailExists(@PathVariable String email) {
        return userSer.checkEmailExists(email);
    }
}
