package com.dentalmoovi.webpage.models.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO {
    private Long idUser;
    private String firstName;
    private String lastName;
    
    @EqualsAndHashCode.Include
    private String email;
    private String celPhone;
    private LocalDate birthday;
    private String gender;
    private String password;
    private String confirmCode;
    private Set<RoleDTO> roles = new HashSet<>();

    
}
