package com.dentalmoovi.webpage.models.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleDTO {
    private Long idRole;
    @EqualsAndHashCode.Include
    private String nameRole;
}
