package com.soft.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Data
@Table(name = "SUPPLIER_ORDER")
public class SupplierOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUPPLIER_ORDER")
    private Long idSupplierOrder;

    @Column(name = "UUID")
    @NotBlank
    private String uuid;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "SUPPLIER_ORDER_STATUS")
    @Enumerated(EnumType.STRING)
    private SupplierOrderStatus supplierOrderStatus;

    @Column(name = "CREATION_DATE")
    @NotNull
    private Date creationDate;

    @Column(name = "SUPPLIER_ORDER_NUMBER")
    @NotBlank
    private Integer supplierOrderNumber;

    @ManyToOne
    @JoinColumn(name = "ID_SUPPLIER", nullable = true)
    private Supplier supplier;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplierOrder")
    private Set<SupplierOrderItem> orderItems = new HashSet<>();

}
