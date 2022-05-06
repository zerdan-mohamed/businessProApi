package com.soft.business.model.user;

import com.soft.business.model.organization.Organization;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter @Setter
@Table(name = "USER")
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "USER_ALIAS")
    @NotBlank
    private String username;

    @Column(name = "USER_EMAIL")
    @NotBlank
    private String email;

    @Column(name = "CODE_TYPE_USER")
    private String codeTypeUser;

    @Column(name = "IS_ENABLED")
    private boolean isEnabled;

    @Column(name = "IS_ACCOUNT_NOT_LOCKED")
    private boolean isAccountNotLocked;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_UUID")
    private String uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRO_ID", updatable = false, insertable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "ORG_ID", updatable = false, insertable = false)
    private Organization organization;
}
