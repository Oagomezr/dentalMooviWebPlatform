package com.dentalmoovi.webpage.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dentalmoovi.webpage.exceptions.DataNotFoundException;
import com.dentalmoovi.webpage.models.dtos.ImagesDTO;
import com.dentalmoovi.webpage.models.dtos.ProductsDTO;
import com.dentalmoovi.webpage.models.entities.Categories;
import com.dentalmoovi.webpage.models.entities.Images;
import com.dentalmoovi.webpage.models.entities.Products;
import com.dentalmoovi.webpage.models.reponses.ProductsResponse;
import com.dentalmoovi.webpage.repositories.ICategoriesRep;
import com.dentalmoovi.webpage.repositories.IProductsRep;

import jakarta.transaction.Transactional;

@Service
public class ProductsSer {
    
    @Autowired
    private IProductsRep productsRep;

    @Autowired
    private ICategoriesRep categoriesRep;

    @Transactional
    public ProductsResponse getProductsByCategory(String parentCategoryName){
        Long numberUpdates = 0L;
        Long maxIdProduct = 0L;
        Categories parentCategory = categoriesRep.findByName(parentCategoryName).orElseThrow(() -> new DataNotFoundException("category not found"));
        List<ProductsDTO> allProductsDTO = new ArrayList<>();
        for(String categoryName : getNamesCategories(parentCategory, parentCategoryName)){
            List<Products> productsByCategory = productsRep.findByCategoryName(categoryName);
            List<ProductsDTO> productsDTO = new ArrayList<>();
            for (Products product : productsByCategory) {
                Set<Images> productImages = product.getProductsImages();
                Set<ImagesDTO> productImagesDTO = new HashSet<>();
                for (Images productImage : productImages) {
                    String base64Image = Base64.getEncoder().encodeToString(productImage.getData());
                    ImagesDTO imageDTO = new ImagesDTO(productImage.getName(), productImage.getContentType(), base64Image);
                    productImagesDTO.add(imageDTO);
                }
                productsDTO.add(new ProductsDTO(product.getNameProduct(), product.getUnitPrice(), product.getDescription(), product.getStock(), productImagesDTO));
                numberUpdates += product.getNumberUpdates();
                if(product.getIdProduct() > maxIdProduct) maxIdProduct = product.getIdProduct();
            }
            allProductsDTO.addAll(productsDTO);
        }
        parentCategory.setCheckProduct(String.valueOf(numberUpdates+maxIdProduct));
        categoriesRep.save(parentCategory);
        return new ProductsResponse(allProductsDTO);
    }

    private Set<String> getNamesCategories(Categories parentCategory, String idParent) {
        List<Categories> children = categoriesRep.findByParentCategoryOrderByName(parentCategory);
        Set<String> ids = new HashSet<>();
        ids.add(idParent);
        for (Categories child : children) {
            ids.add(child.getName());
            ids.addAll(getNamesCategories(child, child.getName()));
        }
        return ids;
    }

    public String checkUpdateByCategory(String name){
        return categoriesRep.findCheckProductosByName(name);
    }
}
