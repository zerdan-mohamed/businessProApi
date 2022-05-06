package com.soft.business.model.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@Table(name = "FUNCTIONALITY")
public class Functionality implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FUN_ID")
    private int id;

    @Column(name = "FUN_LABEL")
    @NotBlank
    private String label;
}
