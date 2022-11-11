package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductFamilyDto implements Serializable {

    private static final String PRODUCT_FAMILY_UUID = "uuid";
    private static final String PRODUCT_FAMILY_NAME = "name";

    @JsonProperty(PRODUCT_FAMILY_UUID)
    private String uuid;

    @JsonProperty(PRODUCT_FAMILY_NAME)
    @NotBlank(message = "Product Family should not be blank.")
    private String name;
}
