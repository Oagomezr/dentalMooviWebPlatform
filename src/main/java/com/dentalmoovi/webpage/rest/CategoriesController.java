package com.dentalmoovi.webpage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dentalmoovi.webpage.models.reponses.CategoriesResponse;
import com.dentalmoovi.webpage.services.CategoriesSer;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriesController {
    
    @Autowired
    private CategoriesSer categoriesSer;

    @GetMapping("/public/categories/checkupdate")
    public ResponseEntity<String> checkUpdateCategories() {
        try{
            return ResponseEntity.ok(categoriesSer.checkUpdate());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoriesResponse> getAllCategories() {
        try{
            return ResponseEntity.ok(categoriesSer.getAllCategories());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/categories/{id}")
    public ResponseEntity<String> getNameCategoryById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(categoriesSer.getNameCategoryById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
