package com.dentalmoovi.webpage.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 70, unique = true)
    private String nameProduct;

    @Column(nullable = false)
    private double unitPrice;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false, length = 50)
    private int stock;

    @Column(length = 50)
    private int numberUpdates;

    @Column(nullable = false)
    private boolean openToPublic;

    @ManyToOne
    @JoinColumn(name = "id_category")
    @JsonIgnore
    private Categories category;

    @OneToMany(mappedBy = "product")
    private List<Images> productsImages;

    @ManyToOne
    @JoinColumn(name = "ID_principalImage")
    private Images principalImage;
}
