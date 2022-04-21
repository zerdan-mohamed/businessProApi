package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierOrderInvoiceDto implements Serializable {

    private static final String SUPPLIER_ORDER_INVOICE_UUID = "uuid";

    @JsonProperty(SUPPLIER_ORDER_INVOICE_UUID)
    private String uuid;
}
