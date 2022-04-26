package com.soft.business.service;

import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.mapper.SupplierOrderItemMapper;
import com.soft.business.model.SupplierOrderItem;
import com.soft.business.repository.SupplierOrderItemRepository;
import com.soft.business.util.validator.SupplierOrderItemValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<SupplierOrderItem> supplierOrderItems = supplierOrderItemRepository.findAll();
        List<SupplierOrderItemDto> supplierOrderItemsDto = new ArrayList<>();

        supplierOrderItems.forEach(
                supplierOrderItem -> supplierOrderItemsDto
                        .add(supplierOrderItemMapper.makeDtoFromSupplierOrderItem(supplierOrderItem))
        );

        return supplierOrderItemsDto;
    }

    @Override
    public SupplierOrderItemDto findSupplierOrderItemByUuid(String uuid) {
        Optional<SupplierOrderItem> supplierOrderItem = supplierOrderItemRepository.findByUuid(uuid);

        return supplierOrderItemMapper.makeDtoFromSupplierOrderItem(supplierOrderItem.get());
    }

    @Override
    public void deleteSupplierOrderItemByUuid(String uuid) {
        // TODO: check delete logic in relation with order status
    }

    @Override
    public SupplierOrderItemDto createSupplierOrderItem(SupplierOrderItemDto supplierOrderItemDto) {
        supplierOrderItemValidator.createSupplierOrderItemValidator(supplierOrderItemDto);
        supplierOrderItemRepository
                .save(supplierOrderItemMapper.makeSupplierOrderItemFromDto(supplierOrderItemDto));

        return supplierOrderItemDto;
    }

    @Override
    public SupplierOrderItemDto updateSupplierOrderItem(
            String uuid, SupplierOrderItemDto supplierOrderItemDto) {

        Optional<SupplierOrderItem> supplierOrderItemDb = this.supplierOrderItemRepository.findByUuid(uuid);

        SupplierOrderItem supplierOrderItem = supplierOrderItemMapper.updateSupplierOrderItem(supplierOrderItemDto, supplierOrderItemDb.get());
        supplierOrderItemRepository.save(supplierOrderItem);

        return supplierOrderItemDto;
    }
}
