package com.dentalmoovi.webpage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dentalmoovi.webpage.services.CacheSer;

@RestController
@RequestMapping
public class CacheController {

    @Autowired
    private CacheSer cacheSer;

    @PostMapping("/public/cache/create")
    public void sendMessage(@RequestBody String email){
        try {
            cacheSer.addToOrUpdateRegistrationCache(email, "101012");
        } catch (Exception e) {
            System.out.println("aqui paso algo");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/public/cache/{email}")
    public ResponseEntity<String> getCache(@PathVariable String email){
        String cacheGetted = cacheSer.getFromRegistrationCache(email);

        if(cacheGetted == null){
            return ResponseEntity.ok("es nulo perro!");
        }

        return ResponseEntity.ok(cacheGetted);
        
    }
}
