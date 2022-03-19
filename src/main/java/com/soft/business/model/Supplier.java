package com.soft.business.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

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
    private String Address;

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

}
