package com.soft.business.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity @Data
@Table(name = "SUPPLIER_ORDER_DETAILS")
public class SupplierOrderParams implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUPPLIER_ORDER_DETAILS")
    private Long idSupplierOrderDetails;

    @Column(name = "UUID")
    @NotBlank
    private String uuid;

    @Column(name = "COUNTER")
    @NotBlank
    private Integer counter;

    @Column(name = "PREFIX")
    @NotBlank
    private String prefix;

}
