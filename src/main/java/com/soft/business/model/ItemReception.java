package com.soft.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@IdClass(ItemReceptionId.class)
@Table(name = "ITEM_RECEPTION")
public class ItemReception implements Serializable {

    @Id
    @Column(name = "RECEPTION_ID")
    private Long receptionId;

    @Id
    @Column(name = "SUPPLIER_ORDER_ITEM_ID")
    private Long supplierOrderItemId;

    @Column(name = "ID_SUPPLIER_ORDER")
    @NotNull
    private Long supplierOrderId;

    @Column(name = "UUID")
    @NotBlank
    private String uuid;

    @Column(name = "QUANTITY")
    @NotNull
    private Double quantity;

    @Column(name = "DATE")
    @NotNull
    private LocalDateTime date;

    // update sql scripts
    @JsonIgnore
    @Column(name = "ORG_ID", updatable = false)
    private int orgId;


}
