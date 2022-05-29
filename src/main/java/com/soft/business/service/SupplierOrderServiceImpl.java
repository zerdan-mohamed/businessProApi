package com.soft.business.service;

import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.mapper.SupplierOrderMapper;
import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderStatus;
import com.soft.business.repository.SupplierOrderRepository;
import com.soft.business.service.organization.OrganizationServiceImpl;
import com.soft.business.util.validator.SupplierOrderValidator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<SupplierOrderDto> findSupplierOrders(Authentication authentication) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);

        List<SupplierOrder> supplierOrders = supplierOrderRepository.findByOrgId(orgId);
        List<SupplierOrderDto> supplierOrdersDto = new ArrayList<>();

        supplierOrders.forEach(
                supplierOrder -> supplierOrdersDto
                        .add(supplierOrderMapper.makeDtoFromSupplierOrder(orgId, supplierOrder))
        );

        return supplierOrdersDto;
    }

    @Override
    public SupplierOrderDto findSupplierOrderByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);

        Optional<SupplierOrder> supplierOrder = supplierOrderRepository.findByUuidAndOrgId(uuid, orgId);

        return supplierOrderMapper.makeDtoFromSupplierOrder(orgId, supplierOrder.get());
    }

    @Override
    @Transactional
    public void deleteSupplierOrderByUuid(Authentication authentication, String uuid) {
        SupplierOrderDto supplierOrder = findSupplierOrderByUuid(authentication, uuid);

        if (!supplierOrder.getSupplierOrderStatus().equals(SupplierOrderStatus.CANCELED)) {
            supplierOrder.setSupplierOrderStatus(SupplierOrderStatus.CANCELED);
        }
    }

    @Override
    public SupplierOrderDto createSupplierOrder(
            Authentication authentication, SupplierOrderDto supplierOrderDto) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);

        supplierOrderValidator.createSupplierOrderValidator(supplierOrderDto);
        supplierOrderRepository
                .save(supplierOrderMapper.makeSupplierOrderFromDto(orgId, supplierOrderDto));

        return supplierOrderDto;
    }

    @Override
    public SupplierOrderDto updateSupplierOrder(
            Authentication authentication, String uuid, SupplierOrderDto supplierOrderDto) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);

        Optional<SupplierOrder> supplierOrderDb = this.supplierOrderRepository.findByUuidAndOrgId(uuid, orgId);

        if (supplierOrderDb.isEmpty()) throw new NoSuchElementException();

        SupplierOrder supplierOrder = supplierOrderMapper.updateSupplierOrder(
                orgId, supplierOrderDto, supplierOrderDb.get()
        );
        supplierOrderRepository.save(supplierOrder);

        return supplierOrderDto;
    }
}
