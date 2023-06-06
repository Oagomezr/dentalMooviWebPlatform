package com.dentalmoovi.webpage.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO {
    private Long idUser;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String celPhone;
    private LocalDate birthday;
    private String gender;
    private String password;
    private Set<RoleDTO> roles = new HashSet<>();
}
