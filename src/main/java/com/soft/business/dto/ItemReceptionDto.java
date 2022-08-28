package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemReceptionDto implements Serializable {

    private static final String RECEPTION_ID = "reception_id";
    private static final String SUPPLIER_ORDER_ITEM_ID = "supplier_order_item_id";
    private static final String ID_SUPPLIER_ORDER = "id_supplier_order";
    private static final String ITEM_RECEPTION_UUID = "uuid";
    private static final String QUANTITY = "quantity";
    private static final String DATE = "date";
    private static final String SUPPLIER_ORDER_ITEM_UUID = "supplier_order_item_uuid";

    @JsonProperty(RECEPTION_ID)
    private Long receptionId;

    @JsonProperty(SUPPLIER_ORDER_ITEM_ID)
    private Long supplierOrderItemId;

    @JsonProperty(ID_SUPPLIER_ORDER)
    private Long supplierOrderId;

    @JsonProperty(ITEM_RECEPTION_UUID)
    private String uuid;

    @JsonProperty(QUANTITY)
    private Double quantity;

    @JsonProperty(DATE)
    private String date;

}
