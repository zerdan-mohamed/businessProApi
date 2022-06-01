package com.soft.business.service.organization;

import com.soft.business.model.SupplierOrder;
import com.soft.business.security.userDetailsService.JpaUserDetails;
import org.springframework.security.core.Authentication;

public interface OrganizationService {

    void incrementPrefix(Integer orgId);

    String makeSupplierOrderNumber(Integer orgId, SupplierOrder supplierOrder);

    static int getOrgIdFromPrincipal(Authentication authentication) {
        return ((JpaUserDetails) authentication.getPrincipal()).getUser().getOrganization().getId();
    }
}
