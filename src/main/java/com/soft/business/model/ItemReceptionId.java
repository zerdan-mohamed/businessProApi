package com.soft.business.model;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class ItemReceptionId implements Serializable {

    // TODO : set uuid instead of id
    private Long receptionId;

    // TODO : set uuid instead of id
    private Long supplierOrderItemId;
}
