package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto implements Serializable {

    private static final String PRODUCT_UUID = "uuid";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_MEASURE_UNITE = "measureUnite";
    private static final String PRODUCT_VAT_RATE = "vatRate";
    private static final String PRODUCT_BUYING_PRICE = "buyingPrice";
    private static final String PRODUCT_RESELLER_PRICE = "resellerPrice";
    private static final String PRODUCT_PUBLIC_SALE_PRICE = "publicSalePrice";
    private static final String PRODUCT_CURRENT_STOCK = "currentStock";
    private static final String PRODUCT_INITIAL_STOCK = "initialStock";
    private static final String PRODUCT_INITIAL_STOCK_DATE = "initialStockDate";
    private static final String PRODUCT_MINIMAL_STOCK = "minimalStock";
    private static final String PRODUCT_MAXIMAL_STOCK = "maximalStock";
    private static final String PRODUCT_CREATION_DATE = "creationDate";
    private static final String ID_PRODUCT_FAMILY = "idProductFamily";

    @NotBlank
    @JsonProperty(PRODUCT_UUID)
    private String uuid;

    @NotBlank
    @JsonProperty(PRODUCT_NAME)
    private String name;

    @JsonProperty(PRODUCT_MEASURE_UNITE)
    private String measureUnite;

    @JsonProperty(PRODUCT_VAT_RATE)
    private Short vatRate;

    @JsonProperty(PRODUCT_BUYING_PRICE)
    private Long buyingPrice;

    @JsonProperty(PRODUCT_RESELLER_PRICE)
    private Long resellerPrice;

    @JsonProperty(PRODUCT_PUBLIC_SALE_PRICE)
    private Long publicSalePrice;

    @JsonProperty(PRODUCT_CURRENT_STOCK)
    private Integer currentStock;

    @JsonProperty(PRODUCT_INITIAL_STOCK)
    private Integer initialStock;

    @JsonProperty(PRODUCT_INITIAL_STOCK_DATE)
    private Date initialStockDate;

    @JsonProperty(PRODUCT_MINIMAL_STOCK)
    private Integer minimalStock;

    @JsonProperty(PRODUCT_MAXIMAL_STOCK)
    private Integer maximalStock;

    @JsonProperty(PRODUCT_CREATION_DATE)
    private Date creationDate;

    @JsonProperty(ID_PRODUCT_FAMILY)
    private String idProductFamily;

}
