package com.soft.business.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

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
    private  String measureUnite;

    @Column(name = "VAT_RATE")
    private Short vatRate;

    @Column(name = "BUYING_PRICE")
    private Long buyingPrice;

    @Column(name = "RESELLER_PRICE")
    private Long resellerPrice;

    @Column(name = "PUBLIC_SALE_PRICE")
    private Long publicSalePrice;

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
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProductFamily")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductFamily productFamily;

}
