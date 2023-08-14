package com.dentalmoovi.webpage.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dentalmoovi.webpage.services.ProductsSer;
import com.dentalmoovi.webpage.dtos.ProductsDTO;
import com.dentalmoovi.webpage.exceptions.DataNotFoundException;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ProductsController {

    @Autowired
    private ProductsSer productsSer;

    @GetMapping("/public/products/{id}")
    public ResponseEntity<Set<ProductsDTO>> getProduct(@PathVariable Long id){
        try {
            Set<ProductsDTO> products = productsSer.getProductsByCategory(id);
            return ResponseEntity.ok(products);
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
