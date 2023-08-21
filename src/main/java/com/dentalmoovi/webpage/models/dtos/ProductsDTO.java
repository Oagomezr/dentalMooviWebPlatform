package com.dentalmoovi.webpage.models.dtos;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductsDTO {
    private String nameProduct;
    private double unitPrice;
    private String description;
    private int stock;
    private Set<ImagesDTO> images;
}
