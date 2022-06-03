package com.soft.business.service;

import com.soft.business.dto.SupplierOrderDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

public interface SupplierOrderService {
    Set<SupplierOrderDto> findSupplierOrders(Authentication authentication);

    SupplierOrderDto findSupplierOrderByUuid(Authentication authentication, String uuid);

    void deleteSupplierOrderByUuid(Authentication authentication, String uuid);

    SupplierOrderDto  createSupplierOrder(Authentication authentication, SupplierOrderDto supplierOrderDto);

    SupplierOrderDto updateSupplierOrder(Authentication authentication, String uuid, SupplierOrderDto supplierOrderDto);
}
