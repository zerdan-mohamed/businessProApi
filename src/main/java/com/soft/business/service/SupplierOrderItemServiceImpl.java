package com.soft.business.service;

import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.mapper.SupplierOrderItemMapper;
import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderItem;
import com.soft.business.model.SupplierOrderStatus;
import com.soft.business.repository.SupplierOrderItemRepository;
import com.soft.business.repository.SupplierOrderRepository;
import com.soft.business.util.validator.SupplierOrderItemValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SupplierOrderItemServiceImpl implements SupplierOrderItemService{

    private SupplierOrderItemRepository supplierOrderItemRepository;

    private SupplierOrderRepository supplierOrderRepository;

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
    public List<SupplierOrderItemDto> findSupplierOrderItems(String supplierOrderUuid) {

        List<SupplierOrderItemDto> supplierOrderItemsDto = new ArrayList<>();

        Optional<SupplierOrder> supplierOrder = supplierOrderRepository.findByUuid(supplierOrderUuid);
        List<SupplierOrderItem> supplierOrderItems = supplierOrderItemRepository.findBySupplierOrder(supplierOrder.get());

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
        SupplierOrderItemDto supplierOrderItem = findSupplierOrderItemByUuid(uuid);

        if (!supplierOrderItem.getSupplierOrder()
                .getSupplierOrderStatus().equals(SupplierOrderStatus.PAID)) {

            long isDeleted = supplierOrderItemRepository.deleteByUuid(uuid);
            if(isDeleted == 0) throw new NoSuchElementException();
        }
    }

    @Override
    public SupplierOrderItemDto createSupplierOrderItem(SupplierOrderItemDto supplierOrderItemDto) {
        supplierOrderItemValidator.createSupplierOrderItemValidator(supplierOrderItemDto);

        supplierOrderItemRepository.save(
                supplierOrderItemMapper.makeSupplierOrderItemFromDto(supplierOrderItemDto)
        );

        return supplierOrderItemDto;
    }

    @Override
    public SupplierOrderItemDto updateSupplierOrderItem(String uuid, SupplierOrderItemDto supplierOrderItemDto) {

        Optional<SupplierOrderItem> supplierOrderItemDb = this.supplierOrderItemRepository.findByUuid(uuid);

        SupplierOrderItem supplierOrderItem = supplierOrderItemMapper.updateSupplierOrderItem(supplierOrderItemDto, supplierOrderItemDb.get());
        supplierOrderItemRepository.save(supplierOrderItem);

        return supplierOrderItemDto;
    }
}
