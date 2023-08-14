package com.dentalmoovi.webpage.services;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dentalmoovi.webpage.dtos.ImagesDTO;
import com.dentalmoovi.webpage.dtos.ProductsDTO;
import com.dentalmoovi.webpage.models.Images;
import com.dentalmoovi.webpage.models.Products;
import com.dentalmoovi.webpage.repositories.IProductsRep;

import jakarta.transaction.Transactional;

@Service
public class ProductsSer {
    
    @Autowired
    private IProductsRep productsRep;

    @Transactional
    public Set<ProductsDTO> getProductsByCategory(Long idCategory){

        List<Products> productsByCategory = productsRep.findByCategoryId(idCategory);
        Set<ProductsDTO> productsDTO = new HashSet<>();
        for (Products product : productsByCategory) {
            Set<Images> productImages = product.getProductsImages();
            Set<ImagesDTO> productImagesDTO = new HashSet<>();
            for (Images productImage : productImages) {
                String base64Image = Base64.getEncoder().encodeToString(productImage.getData());
                ImagesDTO imageDTO = new ImagesDTO(productImage.getName(), productImage.getContentType(), base64Image);
                productImagesDTO.add(imageDTO);
            }
            productsDTO.add(new ProductsDTO(product.getNameProduct(), product.getUnitPrice(), product.getDescription(), product.getStock(), productImagesDTO));
        }
        return productsDTO;
    }
}
