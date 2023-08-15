package com.dentalmoovi.webpage.services;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dentalmoovi.webpage.dtos.ImagesDTO;
import com.dentalmoovi.webpage.dtos.ProductsDTO;
import com.dentalmoovi.webpage.exceptions.DataNotFoundException;
import com.dentalmoovi.webpage.models.Categories;
import com.dentalmoovi.webpage.models.Images;
import com.dentalmoovi.webpage.models.Products;
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
    public Set<ProductsDTO> getProductsByCategory(Long idParentCategory){
        Long numberUpdates = 0L;
        Long maxIdProduct = 0L;
        Categories parentCategory = categoriesRep.findById(idParentCategory).orElseThrow(() -> new DataNotFoundException("category not found"));
        Set<ProductsDTO> allProductsDTO = new HashSet<>();
        for(Long idCategory : getIdsCategories(parentCategory, idParentCategory)){
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
                numberUpdates += product.getNumberUpdates();
                if(product.getIdProduct() > maxIdProduct) maxIdProduct = product.getIdProduct();
            }
            allProductsDTO.addAll(productsDTO);
        }
        parentCategory.setCheckProduct(String.valueOf(numberUpdates+maxIdProduct));
        categoriesRep.save(parentCategory);
        return allProductsDTO;
    }

    private Set<Long> getIdsCategories(Categories parentCategory, Long idParent) {
        List<Categories> children = categoriesRep.findByParentCategory(parentCategory);
        Set<Long> ids = new HashSet<>();
        ids.add(idParent);
        for (Categories child : children) {
            ids.add(child.getIdCategory());
            ids.addAll(getIdsCategories(child, child.getIdCategory()));
        }
        return ids;
    }

    public String checkUpdateByCategory(long id){
        return categoriesRep.findCheckProductosById(id);
    }
}
