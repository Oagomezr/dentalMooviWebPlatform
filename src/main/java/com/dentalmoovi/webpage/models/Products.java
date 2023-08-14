package com.dentalmoovi.webpage.models;

import java.util.Set;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(nullable = false, length = 70)
    private String nameProduct;

    @Column(nullable = false)
    private double unitPrice;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false, length = 50)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "id_category")
    @JsonIgnore
    private Categories category;

    @OneToMany(mappedBy = "product")
    private Set<Images> productsImages;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
        result = prime * result + ((nameProduct == null) ? 0 : nameProduct.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Products other = (Products) obj;
        if (idProduct == null) {
            if (other.idProduct != null)
                return false;
        } else if (!idProduct.equals(other.idProduct))
            return false;
        if (nameProduct == null) {
            if (other.nameProduct != null)
                return false;
        } else if (!nameProduct.equals(other.nameProduct))
            return false;
        return true;
    }

    
}
