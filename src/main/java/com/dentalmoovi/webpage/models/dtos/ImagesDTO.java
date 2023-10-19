package com.dentalmoovi.webpage.models.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ImagesDTO {

    private String name;
    private String contentType;

    @EqualsAndHashCode.Include
    private String imageBase64;
}
