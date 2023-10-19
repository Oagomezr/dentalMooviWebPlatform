package com.dentalmoovi.webpage.models.dtos;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductsDTO {
    @EqualsAndHashCode.Include
    private String nameProduct;
    private double unitPrice;
    private String description;
    private int stock;
    private List<ImagesDTO> images;
    private List<String> location;
}
