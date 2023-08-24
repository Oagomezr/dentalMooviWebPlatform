package com.dentalmoovi.webpage.models.reponses;

import java.util.List;

import com.dentalmoovi.webpage.models.dtos.ProductsDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductsResponse {
    private int amountProducts;
    private int paginatedProducts;
    private List<ProductsDTO> data;
}
