package com.soft.business.service;

import com.soft.business.dto.SupplierOrderItemDto;

import java.text.ParseException;
import java.util.List;

public interface SupplierOrderItemService {

    List<SupplierOrderItemDto> findSupplierOrderItems();

    SupplierOrderItemDto findSupplierOrderItemByUuid(String uuid);

    void deleteSupplierOrderItemByUuid(String uuid);

    SupplierOrderItemDto  createSupplierOrderItem(SupplierOrderItemDto supplierOrderItemDto) throws ParseException;

    SupplierOrderItemDto updateSupplierOrderItem(String uuid, SupplierOrderItemDto supplierOrderItemDto);
}
