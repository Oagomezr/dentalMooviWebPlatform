package com.dentalmoovi.webpage.models.dtos;

import java.util.List;

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
    private List<ImagesDTO> images;
    private List<String> location;
}
