package com.dentalmoovi.webpage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dentalmoovi.webpage.exceptions.DataNotFoundException;
import com.dentalmoovi.webpage.models.reponses.ProductsResponse;
import com.dentalmoovi.webpage.services.ProductsSer;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ProductsController {

    @Autowired
    private ProductsSer productsSer;

    /* @GetMapping("/public/products/{id}")
    public ResponseEntity<Set<ProductsDTO>> getProduct(@PathVariable Long id){
        try {
            Set<ProductsDTO> products = productsSer.getProductsByCategory(id);
            return ResponseEntity.ok(products);
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    } */

    @GetMapping("/public/products/{name}")
    public ResponseEntity<ProductsResponse> getProduct(@PathVariable String name){
        try {
            ProductsResponse products = productsSer.getProductsByCategory(name);
            return ResponseEntity.ok(products);
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/products/checkupdate/{name}")
    public ResponseEntity<String> checkUpdateProductsByCategory(@PathVariable String name){
        return ResponseEntity.ok(productsSer.checkUpdateByCategory(name));
    }
}
