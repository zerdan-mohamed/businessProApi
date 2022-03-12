package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierDto implements Serializable {

    private static final String SUPPLIER_UUID = "uuid";
    private static final String SUPPLIER_NAME = "name";
    private static final String SUPPLIER_CITY = "city";
    private static final String SUPPLIER_PHONE_NUMBER = "phone_number";
    private static final String SUPPLIER_MOBILE_NUMBER = "mobile_number";
    private static final String SUPPLIER_FAX_NUMBER = "fax_number";
    private static final String SUPPLIER_ADDRESS = "address";
    private static final String SUPPLIER_EMAIL = "email";
    private static final String SUPPLIER_WEBSITE = "website";
    private static final String SUPPLIER_CREATION_DATE = "creation_date";
    private static final String CONTACT = "contact";
    private static final String CAPPED_BALANCE = "capped_balance";
    private static final String INITIAL_BALANCE = "initial_balance";
    private static final String PATENT = "patent";
    private static final String ICE = "ice";

    @JsonProperty(SUPPLIER_UUID)
    private String uuid;

    @NotBlank
    @JsonProperty(SUPPLIER_NAME)
    private String name;

    @JsonProperty(SUPPLIER_CITY)
    private String city;

    @JsonProperty(SUPPLIER_PHONE_NUMBER)
    private String phoneNumber;

    @JsonProperty(SUPPLIER_MOBILE_NUMBER)
    private String mobileNumber;

    @JsonProperty(SUPPLIER_FAX_NUMBER)
    private String faxNumber;

    @JsonProperty(SUPPLIER_ADDRESS)
    private String Address;

    @JsonProperty(SUPPLIER_EMAIL)
    private String email;

    @JsonProperty(SUPPLIER_WEBSITE)
    private String website;

    @JsonProperty(CONTACT)
    private String contact;

    @JsonProperty(CAPPED_BALANCE)
    private Double cappedBalance;

    @JsonProperty(INITIAL_BALANCE)
    private Double initialBalance;

    @JsonProperty(PATENT)
    private String patent;

    @JsonProperty(ICE)
    private String ice;

    @JsonProperty(SUPPLIER_CREATION_DATE)
    private String creationDate;
}
