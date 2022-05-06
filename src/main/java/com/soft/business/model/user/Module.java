package com.soft.business.model.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity @Data
@Table(name = "MODULE")
public class Module implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOD_ID")
    private int id;

    @Column(name = "MOD_LABEL")
    @NotBlank
    private String label;
}
