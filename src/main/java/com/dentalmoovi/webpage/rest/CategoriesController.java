package com.dentalmoovi.webpage.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dentalmoovi.webpage.dtos.CategoriesDTO;
import com.dentalmoovi.webpage.services.CategoriesSer;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriesController {
    
    @Autowired
    private CategoriesSer categoriesSer;

    @GetMapping("/public/categories/checkupdate")
    public ResponseEntity<String> checkUpdateCategories() {
        return ResponseEntity.ok(categoriesSer.checkUpdate());
    }

    @GetMapping("/public/categories")
    public ResponseEntity<Set<CategoriesDTO>> getAllCategories() {
        return ResponseEntity.ok(categoriesSer.getAllCategories());
    }
}
