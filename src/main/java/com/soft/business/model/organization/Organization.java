package com.soft.business.model.organization;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "ORGANIZATION")
public class Organization implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORG_ID")
    private int id;

    @Column(name = "ORG_LABEL")
    @NotBlank
    private String label;

    @Column(name = "ORG_CODE_EXTERN")
    @NotBlank
    private String codeExtern;

    @Column(name = "ORG_TYPE")
    private String type;

    @Column(name = "ORG_EMAIL")
    @NotBlank
    private String email;

    @Column(name = "ORG_ADDRESS")
    private String address;

    @Column(name = "ORG_CITY")
    private String city;

    @Column(name = "ORG_POSTAL_CODE")
    private String postalCode;

    @Column(name = "ORG_PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ORG_CREATION_DATE")
    @NotBlank
    private Date creationDate;

    @Column(name = "ORG_LAST_EDITION_DATE")
    private Date lastEditDate;

    @Column(name = "ORG_ACTIVATION_DATE")
    private Date activationDate;

    @Column(name = "ORG_INACTIVATION_DATE")
    private Date inactivationDate;

    @Column(name = "ORG_IS_ACTIVE")
    private boolean isActive;

    @Column(name = "ORG_UUID")
    @NotBlank
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "ORG_ID_GROUP_HEAD")
    private Organization groupHead;

    @ManyToOne
    @JoinColumn(name = "ORG_ID_PARENT")
    private Organization parent;
}
