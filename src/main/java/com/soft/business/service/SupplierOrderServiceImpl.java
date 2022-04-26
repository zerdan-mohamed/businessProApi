package com.soft.business.service;

import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.mapper.SupplierOrderMapper;
import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderStatus;
import com.soft.business.repository.SupplierOrderRepository;
import com.soft.business.util.validator.SupplierOrderValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SupplierOrderServiceImpl implements SupplierOrderService{

    private SupplierOrderRepository supplierOrderRepository;

    private SupplierOrderValidator supplierOrderValidator;

    private SupplierOrderMapper supplierOrderMapper;

    public SupplierOrderServiceImpl(
            SupplierOrderRepository supplierOrderRepository,
            SupplierOrderValidator supplierOrderValidator,
            SupplierOrderMapper supplierOrderMapper) {
        this.supplierOrderRepository = supplierOrderRepository;
        this.supplierOrderValidator = supplierOrderValidator;
        this.supplierOrderMapper = supplierOrderMapper;
    }

    @Override
    public List<SupplierOrderDto> findSupplierOrders() {
        List<SupplierOrder> supplierOrders = supplierOrderRepository.findAll();
        List<SupplierOrderDto> supplierOrdersDto = new ArrayList<>();

        supplierOrders.forEach(
                supplierOrder -> supplierOrdersDto
                        .add(supplierOrderMapper.makeDtoFromSupplierOrder(supplierOrder))
        );

        return supplierOrdersDto;
    }

    @Override
    public SupplierOrderDto findSupplierOrderByUuid(String uuid) {
        Optional<SupplierOrder> supplierOrder = supplierOrderRepository.findByUuid(uuid);

        return supplierOrderMapper.makeDtoFromSupplierOrder(supplierOrder.get());
    }

    @Override
    @Transactional
    public void deleteSupplierOrderByUuid(String uuid) {
        SupplierOrderDto supplierOrder = findSupplierOrderByUuid(uuid);

        if (!supplierOrder.getSupplierOrderStatus().equals(SupplierOrderStatus.CANCELED)) {
            supplierOrder.setSupplierOrderStatus(SupplierOrderStatus.CANCELED);
        }
    }

    @Override
    public SupplierOrderDto createSupplierOrder(SupplierOrderDto supplierOrderDto) {
        supplierOrderValidator.createSupplierOrderValidator(supplierOrderDto);
        supplierOrderRepository
                .save(supplierOrderMapper.makeSupplierOrderFromDto(supplierOrderDto));

        return supplierOrderDto;
    }

    @Override
    public SupplierOrderDto updateSupplierOrder(String uuid, SupplierOrderDto supplierOrderDto) {
        Optional<SupplierOrder> supplierOrderDb = this.supplierOrderRepository.findByUuid(uuid);

        if (supplierOrderDb.isEmpty()) throw new NoSuchElementException();

        SupplierOrder supplierOrder = supplierOrderMapper.updateSupplierOrder(supplierOrderDto, supplierOrderDb.get());
        supplierOrderRepository.save(supplierOrder);

        return supplierOrderDto;
    }
}
