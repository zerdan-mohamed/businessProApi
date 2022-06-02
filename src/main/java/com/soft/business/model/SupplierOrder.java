package com.soft.business.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "SUPPLIER_ORDER")
public class SupplierOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUPPLIER_ORDER")
    private Long idSupplierOrder;

    @Column(name = "UUID")
    @NotNull
    private String uuid;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "SUPPLIER_ORDER_STATUS")
    private Integer supplierOrderStatus;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "SUPPLIER_ORDER_NUMBER")
    private String supplierOrderNumber;

    @Column(name = "ORG_ID", updatable = false)
    private int orgId;

    @ManyToOne
    @JoinColumn(name = "ID_SUPPLIER")
    private Supplier supplier;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplierOrder")
    private Set<SupplierOrderItem> orderItems = new HashSet<>();
}
