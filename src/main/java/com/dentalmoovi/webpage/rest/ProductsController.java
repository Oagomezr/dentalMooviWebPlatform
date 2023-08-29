package com.dentalmoovi.webpage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dentalmoovi.webpage.models.dtos.ProductsDTO;
import com.dentalmoovi.webpage.models.reponses.ProductsResponse;
import com.dentalmoovi.webpage.services.ProductsSer;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ProductsController {

    @Autowired
    private ProductsSer productsSer;

    @GetMapping("/public/products/search/{name}/{limit}/{pageNumber}/{pageSize}")
    public ResponseEntity<ProductsResponse> getProductsByContaining(@PathVariable String name, @PathVariable boolean limit, @PathVariable int pageNumber, @PathVariable int pageSize){
        try {
            ProductsResponse products = productsSer.getProductsByContaining(name, limit, pageNumber, pageSize);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/products/{name}")
    public ResponseEntity<ProductsDTO> getProduct(@PathVariable String name){
        try {
            ProductsDTO products = productsSer.getProduct(name);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/products/category/{name}/{pageNumber}/{pageSize}")
    public ResponseEntity<ProductsResponse> getProductsByCategory(
        @PathVariable String name, @PathVariable int pageNumber, @PathVariable int pageSize){
        try {
            ProductsResponse products = productsSer.getProductsByCategory(name, pageNumber, pageSize);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
