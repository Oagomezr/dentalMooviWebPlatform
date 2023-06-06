package com.dentalmoovi.webpage.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(nullable = false, length = 25)
    private String username;

    @Column(nullable = false, length = 25)
    private String firstName;

    @Column(nullable = false, length = 25)
    private String lastName;

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false, length = 10)
    private String celPhone;

    @Column(nullable = true)
    private LocalDate birthday;

    @Column(nullable = false, length = 15)
    private String gender;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "users_roles",
                joinColumns = { @JoinColumn(name = "id_user", referencedColumnName = "id_user") },
                inverseJoinColumns = { @JoinColumn(name = "id_role", referencedColumnName = "id_role") })
    private Set<Roles> roles = new HashSet<>();
}
