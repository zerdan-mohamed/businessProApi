package com.soft.business.service.organization;

import com.soft.business.security.userDetailsService.JpaUserDetails;
import org.springframework.security.core.Authentication;

public interface OrganizationService {

    static int getOrgIdFromPrincipal(Authentication authentication) {
        return ((JpaUserDetails) authentication.getPrincipal()).getUser().getOrganization().getId();
    }
}
