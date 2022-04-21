package com.soft.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Data
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCT")
    private Long idProduct;

    @Column(name = "UUID")
    @NotBlank
    private String uuid;

    @Column(name = "NAME")
    @NotBlank
    private String name;

    @Column(name = "MEASURE_UNITE")
    private String measureUnite;

    @Column(name = "VAT_RATE")
    private Double vatRate;

    @Column(name = "BUYING_PRICE")
    private Double buyingPrice;

    @Column(name = "RESELLER_PRICE")
    private Double resellerPrice;

    @Column(name = "PUBLIC_SALE_PRICE")
    private Double publicSalePrice;

    @Column(name = "CURRENT_STOCK")
    private Integer currentStock;

    @Column(name = "INITIAL_STOCK")
    private Integer initialStock;

    @Column(name = "INITIAL_STOCK_DATE")
    private Date initialStockDate;

    @Column(name = "MINIMAL_STOCK")
    private Integer minimalStock;

    @Column(name = "MAXIMAL_STOCK")
    private Integer maximalStock;

    @Column(name = "CREATION_DATE")
    @NotNull
    private Date creationDate;

    @ManyToOne()
    @JoinColumn(name = "ID_PRODUCT_FAMILY", nullable = true)
    private ProductFamily productFamily;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<SupplierOrderItem> orderItems = new HashSet<>();
}
