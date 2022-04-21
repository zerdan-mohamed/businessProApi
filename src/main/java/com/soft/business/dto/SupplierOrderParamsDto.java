package com.soft.business.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierOrderParamsDto implements Serializable {

    private static final String SUPPLIER_ORDER_DETAILS_UUID = "uuid";
    private static final String COUNTER = "counter";
    private static final String PREFIX = "prefix";

    @JsonProperty(SUPPLIER_ORDER_DETAILS_UUID)
    private String uuid;

    @JsonProperty(COUNTER)
    private Integer counter;

    @JsonProperty(PREFIX)
    private String prefix;

}
