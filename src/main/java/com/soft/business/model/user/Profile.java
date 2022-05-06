package com.soft.business.model.user;

import com.soft.business.model.organization.Organization;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "PROFILE")
public class Profile implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private int id;

    @Column(name = "PRO_LABEL")
    @NotBlank
    private String label;

    @Column(name = "CODE_TYPE_USER")
    private String codeTypeUser;

    @ManyToOne
    @JoinColumn(name = "ORG_ID", updatable = false, insertable = false)
    private Organization organization;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PRO_ROLE",
               joinColumns = @JoinColumn(name = "PRO_ID"),
               inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roles;
}
