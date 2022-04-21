package com.soft.business.service;

import com.soft.business.dto.SupplierOrderDto;


import java.text.ParseException;
import java.util.List;

public interface SupplierOrderService {
    List<SupplierOrderDto> findSupplierOrders();

    SupplierOrderDto findSupplierOrderByUuid(String uuid);

    void deleteSupplierOrderByUuid(String uuid);

    SupplierOrderDto  createSupplierOrder(SupplierOrderDto supplierOrderDto) throws ParseException;

    SupplierOrderDto updateSupplierOrder(String uuid, SupplierOrderDto supplierOrderDto);
}
