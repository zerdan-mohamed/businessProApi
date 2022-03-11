package com.soft.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="PRODUCT_FAMILY")
public class ProductFamily implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCT_FAMILY")
    private Long idProductFamily;

    @Column(name = "UUID")
    @NotBlank
    private String uuid;

    @Column(name = "NAME")
    @NotBlank
    private  String name;

    @JsonIgnore
    @OneToMany(mappedBy = "productFamily", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Product> products;
}
