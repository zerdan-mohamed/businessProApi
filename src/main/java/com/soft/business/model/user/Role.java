package com.soft.business.model.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "ROLE")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private int id;

    @Column(name = "ROLE_LABEL")
    @NotBlank
    private String label;

    @Column(name = "CODE_TYPE_USER")
    private String codeTypeUser;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_FUN",
               joinColumns = @JoinColumn(name = "ROLE_ID"),
               inverseJoinColumns = @JoinColumn(name = "FUN_ID"))
    private List<Functionality> functionalities;
}
