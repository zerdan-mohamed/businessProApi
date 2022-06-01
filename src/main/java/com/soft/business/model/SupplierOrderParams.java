package com.soft.business.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity @Data
@Table(name = "SUPPLIER_ORDER_PARAMS")
public class SupplierOrderParams implements Serializable {

    @Id
    @Column(name = "ORG_ID", updatable = false, insertable = false)
    @NotNull
    private int orgId;

    @Column(name = "COUNTER", insertable = false)
    @NotNull
    private Integer counter;

    @Column(name = "PREFIX", updatable = false, insertable = false)
    @NotNull
    private String prefix;
}
