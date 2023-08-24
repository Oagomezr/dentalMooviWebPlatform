package com.dentalmoovi.webpage.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ProductsResponse getProductsByCategory(String parentCategoryName, int currentPage, int productsPerPage){
        class GetProductsByCategory{
            ProductsResponse getProductsByCategory() {
                Categories parentCategory = categoriesRep.findByName(parentCategoryName)
                        .orElseThrow(() -> new RuntimeException("Category not found"));
            
                List<Products> allProducts = new ArrayList<>();

                List<String> listAllSubCategories = getNamesCategories(parentCategory);
                listAllSubCategories.add(parentCategoryName);
            
                for (String categoryName : listAllSubCategories)
                    allProducts.addAll(productsRep.findByCategoryName(categoryName));
                
                Collections.sort(allProducts, (product1, product2) -> product1.getNameProduct().compareTo(product2.getNameProduct()));

                int startIndex = (currentPage - 1) * productsPerPage;
                int endIndex = Math.min(startIndex + productsPerPage, allProducts.size());
                List<Products> currentPageProducts = allProducts.subList(startIndex, endIndex);
                
                List<ProductsDTO> productsDTO = convertToProductsDTOList(currentPageProducts);
                
                return new ProductsResponse(allProducts.size(),productsDTO.size(), productsDTO);
            }

            private List<String> getNamesCategories(Categories parentCategory) {
                List<Categories> children = categoriesRep.findByParentCategoryOrderByName(parentCategory);
                List<String> names = new ArrayList<>();
                for (Categories child : children) {
                    names.add(child.getName());
                    names.addAll(getNamesCategories(child));
                }
                return names;
            }
            
        }
        GetProductsByCategory innerClass = new GetProductsByCategory();
        return innerClass.getProductsByCategory();
    }

    @Transactional
    public ProductsDTO getProduct(String name){

        class GetProduct{
            ProductsDTO getProduct(){
                Products product = productsRep.findByNameProduct(name)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
                Categories category = product.getCategory();
                List<String> location = getLocationProduct(category);
                List<ImagesDTO> productImagesDTO = getProductImages(product.getProductsImages());

                return new ProductsDTO(name, product.getUnitPrice(), product.getDescription(), product.getStock(), productImagesDTO, location);
            }

            private List<String> getLocationProduct(Categories category){
                List<String> location = new ArrayList<>();
                location.add(category.getName());
                if(category.getParentCategory() != null) location.addAll(getLocationProduct(category.getParentCategory()));
                return location;
            }
        }

        GetProduct innerClass = new GetProduct();
        return innerClass.getProduct();
    }

    private List<ImagesDTO> getProductImages(Set<Images> productImages){
        List<ImagesDTO> productImagesDTO = new ArrayList<>();
        for (Images productImage : productImages) {
            String base64Image = Base64.getEncoder().encodeToString(productImage.getData());
            ImagesDTO imageDTO = new ImagesDTO(productImage.getName(), productImage.getContentType(), base64Image);
            productImagesDTO.add(imageDTO);
        }
        return productImagesDTO;
    }

    @Transactional
    public ProductsResponse getProductsByContaining(String name, boolean limit){
        List<Products> productsFound;
        if(limit){
            productsFound = productsRep.findTop7ByNameProductContainingIgnoreCase(name);
        }else{
            productsFound = productsRep.findByNameProductContainingIgnoreCase(name);
        }
        
        List<ProductsDTO> productsDTO = convertToProductsDTOList(productsFound);

        return new ProductsResponse(0, productsDTO.size(), productsDTO);
    }

    private List<ProductsDTO> convertToProductsDTOList(List<Products> productsList) {
        List<ProductsDTO> productsDTOList = new ArrayList<>();
    
        for (Products product : productsList) {
            List<ImagesDTO> productImagesDTO = getProductImages(product.getProductsImages());
            productsDTOList.add(new ProductsDTO(product.getNameProduct(), product.getUnitPrice(), product.getDescription(), product.getStock(), productImagesDTO, null));
        }
    
        return productsDTOList;
    }
}
