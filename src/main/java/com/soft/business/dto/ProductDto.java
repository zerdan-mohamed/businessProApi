package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto implements Serializable {

    private static final String PRODUCT_UUID = "uuid";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_MEASURE_UNITE = "measure_unite";
    private static final String PRODUCT_VAT_RATE = "vat_rate";
    private static final String PRODUCT_BUYING_PRICE = "buying_price";
    private static final String PRODUCT_RESELLER_PRICE = "reseller_price";
    private static final String PRODUCT_PUBLIC_SALE_PRICE = "public_sale_price";
    private static final String PRODUCT_CURRENT_STOCK = "current_stock";
    private static final String PRODUCT_INITIAL_STOCK = "initial_stock";
    private static final String PRODUCT_INITIAL_STOCK_DATE = "initial_stock_date";
    private static final String PRODUCT_MINIMAL_STOCK = "minimal_stock";
    private static final String PRODUCT_MAXIMAL_STOCK = "maximal_stock";
    private static final String PRODUCT_CREATION_DATE = "creation_date";
    private static final String PRODUCT_FAMILY = "product_family";
    private static final String ORG_ID = "org_id";

    @JsonProperty(PRODUCT_UUID)
    private String uuid;

    @JsonProperty(PRODUCT_NAME)
    private String name;

    @JsonProperty(PRODUCT_MEASURE_UNITE)
    private String measureUnite;

    @JsonProperty(PRODUCT_VAT_RATE)
    private Double vatRate;

    @JsonProperty(PRODUCT_BUYING_PRICE)
    private Double buyingPrice;

    @JsonProperty(PRODUCT_RESELLER_PRICE)
    private Double resellerPrice;

    @JsonProperty(PRODUCT_PUBLIC_SALE_PRICE)
    private Double publicSalePrice;

    @JsonProperty(PRODUCT_CURRENT_STOCK)
    private Integer currentStock;

    @JsonProperty(PRODUCT_INITIAL_STOCK)
    private Integer initialStock;

    @JsonProperty(PRODUCT_INITIAL_STOCK_DATE)
    private String initialStockDate;

    @JsonProperty(PRODUCT_MINIMAL_STOCK)
    private Integer minimalStock;

    @JsonProperty(PRODUCT_MAXIMAL_STOCK)
    private Integer maximalStock;

    @JsonProperty(PRODUCT_CREATION_DATE)
    private Date creationDate;

    @JsonProperty(ORG_ID)
    private int orgId;

    @JsonProperty(PRODUCT_FAMILY)
    private ProductFamilyDto productFamily;
}
