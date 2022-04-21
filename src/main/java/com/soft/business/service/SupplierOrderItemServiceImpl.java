package com.soft.business.service;

import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.mapper.SupplierOrderItemMapper;
import com.soft.business.repository.SupplierOrderItemRepository;
import com.soft.business.util.validator.SupplierOrderItemValidator;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class SupplierOrderItemServiceImpl implements SupplierOrderItemService{

    private SupplierOrderItemRepository supplierOrderItemRepository;

    private SupplierOrderItemValidator supplierOrderItemValidator;

    private SupplierOrderItemMapper supplierOrderItemMapper;

    public SupplierOrderItemServiceImpl(
            SupplierOrderItemRepository supplierOrderItemRepository,
            SupplierOrderItemValidator supplierOrderItemValidator,
            SupplierOrderItemMapper supplierOrderItemMapper) {
        this.supplierOrderItemRepository = supplierOrderItemRepository;
        this.supplierOrderItemValidator = supplierOrderItemValidator;
        this.supplierOrderItemMapper = supplierOrderItemMapper;
    }

    @Override
    public List<SupplierOrderItemDto> findSupplierOrderItems() {
        return null;
    }

    @Override
    public SupplierOrderItemDto findSupplierOrderItemByUuid(String uuid) {
        return null;
    }

    @Override
    public void deleteSupplierOrderItemByUuid(String uuid) {

    }

    @Override
    public SupplierOrderItemDto createSupplierOrderItem(SupplierOrderItemDto supplierOrderItemDto) throws ParseException {
        return null;
    }

    @Override
    public SupplierOrderItemDto updateSupplierOrderItem(String uuid, SupplierOrderItemDto supplierOrderItemDto) {
        return null;
    }
}
