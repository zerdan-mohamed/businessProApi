package com.soft.business.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity @Data
@Table(name = "SUPPLIER_ORDER_ITEM")
public class SupplierOrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUPPLIER_ORDER_ITEM")
    private Long idSupplierOrderItem;

    @Column(name = "UUID")
    @NotNull
    private String uuid;

    @Column(name = "QUANTITY")
    private Double quantity;

    @Column(name = "MEASURE_UNITE")
    private String measureUnite;

    @Column(name = "PRICE_HT")
    private Double priceHT;

    @Column(name = "VAT_RATE")
    private Double vatRate;

    @Column(name = "DISCOUNT")
    private Double discount;

    @Column(name = "ORG_ID", updatable = false)
    @NotNull
    private int orgId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ID_PRODUCT")
    private Product product;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ID_SUPPLIER_ORDER")
    private SupplierOrder supplierOrder;

}
