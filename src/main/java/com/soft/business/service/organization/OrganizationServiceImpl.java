package com.soft.business.service.organization;

import com.soft.business.security.userDetailsService.JpaUserDetails;
import org.springframework.security.core.Authentication;

public class OrganizationServiceImpl implements OrganizationService {


    public static int getOrgIdFromPrincipal(Authentication authentication) {
        return ((JpaUserDetails) authentication.getPrincipal()).getUser().getOrganization().getId();
    }
}
