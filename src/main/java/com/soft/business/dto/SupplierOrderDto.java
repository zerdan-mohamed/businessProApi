package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierOrderDto implements Serializable {

    private static final String SUPPLIER_ORDER_UUID = "uuid";
    private static final String COMMENT = "comment";
    private static final String SUPPLIER_ORDER_STATUS = "supplier_order_status";
    private static final String CREATION_DATE = "creation_date";
    private static final String SUPPLIER_ORDER_NUMBER = "supplier_order_number";
    private static final String SUPPLIER = "supplier";

    @JsonProperty(SUPPLIER_ORDER_UUID)
    private String uuid;

    @JsonProperty(COMMENT)
    private String comment;

    @JsonProperty(SUPPLIER_ORDER_STATUS)
    private Integer supplierOrderStatus;

    @JsonProperty(CREATION_DATE)
    private String creationDate;

    @JsonProperty(SUPPLIER_ORDER_NUMBER)
    private String supplierOrderNumber;

    @JsonProperty(SUPPLIER)
    private SupplierDto supplier;

}
