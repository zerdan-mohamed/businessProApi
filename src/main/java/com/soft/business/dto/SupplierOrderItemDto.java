package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierOrderItemDto implements Serializable {

    private static final String SUPPLIER_ORDER_ITEM_UUID = "uuid";
    private static final String QUANTITY = "quantity";
    private static final String MEASURE_UNITE = "measure_unite";
    private static final String PRICE_HT = "price_ht";
    private static final String VAT_RATE = "vat_rate";
    private static final String DISCOUNT = "discount";
    private static final String SUPPLIER_ORDER_ITEM_STATUS = "supplier_order_item_status";
    private static final String PRODUCT = "product";
    private static final String SUPPLIER_ORDER = "supplier_order";

    @JsonProperty(SUPPLIER_ORDER_ITEM_UUID)
    private String uuid;

    @JsonProperty(QUANTITY)
    private Double quantity;

    @JsonProperty(MEASURE_UNITE)
    private String measureUnite;

    @JsonProperty(PRICE_HT)
    private Double priceHT;

    @JsonProperty(VAT_RATE)
    private Double vatRate;

    @JsonProperty(DISCOUNT)
    private Double discount;

    @JsonProperty(SUPPLIER_ORDER_ITEM_STATUS)
    private Integer supplierOrderItemStatus;

    @JsonProperty(PRODUCT)
    private ProductDto product;

    @JsonProperty(SUPPLIER_ORDER)
    private SupplierOrderDto supplierOrder;

}
