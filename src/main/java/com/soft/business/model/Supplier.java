package com.soft.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Data
@Table(name = "SUPPLIER")
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID")
    @NotBlank
    private String uuid;

    @Column(name = "NAME")
    @NotBlank
    private String name;

    @Column(name = "CITY")
    private String city;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "FAX_NUMBER")
    private String faxNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    @Email
    private String email;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "CREATION_DATE")
    @NotNull
    private Date creationDate;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "CAPPED_BALANCE")
    @PositiveOrZero
    private Double cappedBalance;

    @Column(name = "INITIAL_BALANCE")
    @PositiveOrZero
    private Double initialBalance;

    @Column(name = "PATENT")
    private String patent;

    @Column(name = "ICE")
    private String ice;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
    private Set<SupplierOrder> supplierOrders = new HashSet<>();

}
