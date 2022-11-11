package com.soft.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="PRODUCT_FAMILY")
public class ProductFamily implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCT_FAMILY")
    private Long idProductFamily;

    @Column(name = "UUID", updatable = false)
    @NotBlank
    private String uuid;

    @Column(name = "NAME")
    @NotBlank
    private  String name;

    @JsonIgnore
    @Column(name = "ORG_ID", updatable = false)
    private int orgId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productFamily")
    private Set<Product> products = new HashSet<>();
}
