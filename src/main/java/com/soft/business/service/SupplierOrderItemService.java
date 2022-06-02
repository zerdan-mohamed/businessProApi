package com.soft.business.service;

import com.soft.business.dto.SupplierOrderItemDto;
import org.springframework.security.core.Authentication;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface SupplierOrderItemService {

    List<SupplierOrderItemDto> findSupplierOrderItems(Authentication authentication, String supplierOrderUuid);

    SupplierOrderItemDto findSupplierOrderItemByUuid(Authentication authentication, String uuid);

    void deleteSupplierOrderItemByUuid(Authentication authentication, String uuid);

    Set<SupplierOrderItemDto>  createSupplierOrderItem(Authentication authentication, List<SupplierOrderItemDto> supplierOrderItemDto) throws ParseException;

    SupplierOrderItemDto updateSupplierOrderItem(Authentication authentication, String uuid, SupplierOrderItemDto supplierOrderItemDto);
}
