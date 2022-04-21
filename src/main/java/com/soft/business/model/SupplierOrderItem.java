package com.soft.business.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity @Data
@Table(name = "SUPPLIER_ORDER_ITEM")
public class SupplierOrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUPPLIER_ORDER_ITEM")
    private Long idSupplierOrderItem;

    @Column(name = "UUID")
    @NotBlank
    private String uuid;

    @Column(name = "QUANTITY")
    @NotBlank
    private Double quantity;

    @Column(name = "MEASURE_UNITE")
    private String measureUnite;

    @Column(name = "PRICE_HT")
    @NotBlank
    private Double priceHT;

    @Column(name = "VAT_RATE")
    @NotBlank
    private Double vatRate;

    @Column(name = "DISCOUNT")
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT", nullable = true)
    private Product product;

    @ManyToOne
    @NotBlank
    @JoinColumn(name = "ID_SUPPLIER_ORDER", nullable = true)
    private SupplierOrder supplierOrder;

}
