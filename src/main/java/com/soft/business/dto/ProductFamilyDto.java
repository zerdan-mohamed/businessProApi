package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.soft.business.model.Product;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

public class ProductFamilyDto implements Serializable {

    private static final String PRODUCT_FAMILY_UUID = "uuid";
    private static final String PRODUCT_FAMILY_NAME = "name";
    private static final String PRODUCTS = "products";

    @NotBlank
    @JsonProperty(PRODUCT_FAMILY_UUID)
    private String uuid;

    @NotBlank
    @JsonProperty(PRODUCT_FAMILY_NAME)
    private  String name;

    @JsonProperty(PRODUCTS)
    private List<Product> products;

}
