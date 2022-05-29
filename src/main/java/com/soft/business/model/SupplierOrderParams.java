package com.soft.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity @Data
@Table(name = "SUPPLIER_ORDER_PARAMS")
public class SupplierOrderParams implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUPPLIER_ORDER_PARAMS")
    private Long idSupplierOrderParams;

    @Column(name = "UUID")
    @NotBlank
    private String uuid;

    @Column(name = "COUNTER")
    @NotBlank
    private Integer counter;

    @Column(name = "PREFIX")
    @NotBlank
    private String prefix;

    @JsonIgnore
    @Column(name = "ORG_ID", updatable = false, insertable = true)
    private int orgId;

}
