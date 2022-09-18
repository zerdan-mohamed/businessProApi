package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceptionDto implements Serializable {

    private static final String RECEPTION_UUID = "uuid";
    private static final String CREATION_DATE = "creation_date";
    private static final String DESCRIPTION = "description";
    private static final String SUPPLIER_ID = "supplier_id";
    private static final String RECEPTION_ITEMS = "reception_items";

    @JsonProperty(RECEPTION_UUID)
    private String uuid;

    @JsonProperty(CREATION_DATE)
    private String creationDate;

    @JsonProperty(DESCRIPTION)
    private String description;

    // FIXME : use uuid instead of id
    @JsonProperty(SUPPLIER_ID)
    private Long supplierId;

    @JsonProperty(RECEPTION_ITEMS)
    private Set<ItemReceptionDto> receptionItems = new HashSet<>();

}
