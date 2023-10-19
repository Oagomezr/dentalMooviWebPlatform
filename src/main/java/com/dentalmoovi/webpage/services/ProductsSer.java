package com.dentalmoovi.webpage.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(cacheNames = "productsByCategory")
    public ProductsResponse getProductsByCategory(String parentCategoryName, int currentPage, int productsPerPage){
        class GetProductsByCategory{
            ProductsResponse getProductsByCategory() {

                //Search category inside database
                Categories parentCategory = categoriesRep.findByName(parentCategoryName)
                        .orElseThrow(() -> new RuntimeException("Category not found"));
            
                //prepare the list of products
                List<Products> allProducts = new ArrayList<>();

                //We need to have all category's products, so we need to have all category's subcategories
                List<String> listAllSubCategories = getNamesCategories(parentCategory);
                listAllSubCategories.add(parentCategoryName);
            
                /*We have products in each subcategory, 
                so we loop all subcategories and recovery the products in a list of products*/
                for (String categoryName : listAllSubCategories)
                    allProducts.addAll(productsRep.findByCategoryName(categoryName));
                
                //We have all products but unorganized, we organize them alphabetical
                Collections.sort(allProducts, (product1, product2) -> product1.getNameProduct().compareTo(product2.getNameProduct()));

                /*We cannot show the costumer N amount of products if N is a high number,
                so we need to do pagination*/
                int startIndex = (currentPage - 1) * productsPerPage;
                int endIndex = Math.min(startIndex + productsPerPage, allProducts.size());
                List<Products> currentPageProducts = allProducts.subList(startIndex, endIndex);
                
                //Classic DTO
                List<ProductsDTO> productsDTO = convertToProductsDTOList(currentPageProducts);

                /*One of the best practices in programming is not send List or Arrays as response,
                instead Objects as response*/
                ProductsResponse productsResponse = new ProductsResponse();
                productsResponse.setAmountProducts(allProducts.size());
                productsResponse.setPaginatedProducts(productsDTO.size());
                productsResponse.setData(productsDTO);
                
                return productsResponse;
            }

            //Get all subcategories
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
    @Cacheable(cacheNames = "getProduct")
    public ProductsDTO getProduct(String name){

        class GetProduct{
            ProductsDTO getProduct(){

                //Search product
                Products product = productsRep.findByNameProduct(name)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
                //Get product's category
                Categories category = product.getCategory();

                //Get location producto since parent category until its subcategory
                List<String> location = getLocationProduct(category);

                //Get Product images
                List<ImagesDTO> productImagesDTO = getProductImages(product, true);

                ProductsDTO productsDTO = new ProductsDTO();
                productsDTO.setNameProduct(name);
                productsDTO.setUnitPrice(product.getUnitPrice());
                productsDTO.setDescription(product.getDescription());
                productsDTO.setStock(product.getStock());
                productsDTO.setImages(productImagesDTO);
                productsDTO.setLocation(location);

                return productsDTO;
            }

            //It's a function with the aim of find the location products inside the categories
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

    //This function allow us to find one or all product images
    private List<ImagesDTO> getProductImages(Products product, boolean allImages){
        List<ImagesDTO> productImagesDTO = new ArrayList<>();
        if(allImages){
            for (Images productImage : product.getProductsImages()) {
                
                String base64Image = Base64.getEncoder().encodeToString(productImage.getData());
                ImagesDTO imageDTO = new ImagesDTO();
                imageDTO.setName(productImage.getName());
                imageDTO.setContentType(productImage.getContentType());
                imageDTO.setImageBase64(base64Image);

                if (product.getPrincipalImage() == productImage)
                    productImagesDTO.add(0, imageDTO);
                else
                    productImagesDTO.add(imageDTO);
            }
        }else if(product.getPrincipalImage() != null){
            String base64Image = Base64.getEncoder().encodeToString(product.getPrincipalImage().getData());
            ImagesDTO imageDTO = new ImagesDTO();
            imageDTO.setName(product.getPrincipalImage().getName());
            imageDTO.setContentType(product.getPrincipalImage().getContentType());
            imageDTO.setImageBase64(base64Image);
            productImagesDTO.add(imageDTO);
        }
        
        return productImagesDTO;
    }

    @Transactional
    @Cacheable(cacheNames = "getProducsByContaining")
    public ProductsResponse getProductsByContaining(String name, boolean limit, int currentPage, int productsPerPage){
        List<Products> productsFound;

        //if the user wants to obtain only the 7 first query results  or all results
        if(limit){

            //Get the first 7 query results from database
            productsFound = productsRep.findTop7ByNameProductContainingIgnoreCase(name);

            //Convert those results in DTOs
            List<ProductsDTO> productsDTO = convertToProductsDTOList(productsFound);

            //Put the DTOs inside the Object
            ProductsResponse productsResponse = new ProductsResponse();
            productsResponse.setAmountProducts(0);
            productsResponse.setPaginatedProducts(productsDTO.size());
            productsResponse.setData(productsDTO);
            return productsResponse;

        }else{

            //Get all query results
            List<Products> allProductsFound = productsRep.findByNameProductContainingIgnoreCase(name);

            //Do pagination
            int startIndex = (currentPage - 1) * productsPerPage;
            int endIndex = Math.min(startIndex + productsPerPage, allProductsFound.size());
            productsFound = allProductsFound.subList(startIndex, endIndex);

            //Convert those results in DTOs
            List<ProductsDTO> productsDTO = convertToProductsDTOList(productsFound);

            //Put the DTOs inside the Object
            ProductsResponse productsResponse = new ProductsResponse();
            productsResponse.setAmountProducts(allProductsFound.size());
            productsResponse.setPaginatedProducts(productsDTO.size());
            productsResponse.setData(productsDTO);
            return productsResponse;
        }
        
        
    }

    //This only converts our database data to DTOs
    private List<ProductsDTO> convertToProductsDTOList(List<Products> productsList) {
        List<ProductsDTO> productsDTOList = new ArrayList<>();
    
        for (Products product : productsList) {
            if(product.isOpenToPublic()){
                List<ImagesDTO> productImagesDTO = getProductImages(product, false);
                ProductsDTO productDTO = new ProductsDTO();
                productDTO.setNameProduct(product.getNameProduct());
                productDTO.setUnitPrice(product.getUnitPrice());
                productDTO.setDescription(product.getDescription());
                productDTO.setStock(product.getStock());
                productDTO.setImages(productImagesDTO);
                productsDTOList.add(productDTO);
            }
        }
    
        return productsDTOList;
    }
}
