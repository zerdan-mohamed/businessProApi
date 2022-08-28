package com.soft.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "RECEPTION")
public class Reception implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECEPTION_ID")
    private Long receptionId;

    @Column(name = "UUID")
    @NotBlank
    private String uuid;

    @Column(name = "CREATION_DATE")
    @NotNull
    private LocalDateTime creationDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SUPPLIER_ID", updatable = false)
    private Long supplierId;

    @JsonIgnore
    @Column(name = "ORG_ID", updatable = false)
    private int orgId;

}
