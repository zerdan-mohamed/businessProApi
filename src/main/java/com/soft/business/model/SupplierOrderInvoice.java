package com.soft.business.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity @Data
@Table(name = "SUPPLIER_ORDER_INVOICE")
public class SupplierOrderInvoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUPPLIER_ORDER_INVOICE")
    private Long idSupplierOrderInvoice;

    @Column(name = "UUID")
    @NotBlank
    private String uuid;
}
