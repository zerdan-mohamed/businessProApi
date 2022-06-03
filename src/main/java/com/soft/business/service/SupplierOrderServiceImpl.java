package com.soft.business.service;

import com.soft.business.dto.SupplierOrderDto;
import com.soft.business.mapper.SupplierOrderMapper;
import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderStatus;
import com.soft.business.repository.SupplierOrderRepository;
import com.soft.business.service.organization.OrganizationService;
import com.soft.business.util.validator.SupplierOrderValidator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SupplierOrderServiceImpl implements SupplierOrderService{

    private final SupplierOrderRepository supplierOrderRepository;
    private final SupplierOrderValidator supplierOrderValidator;
    private final SupplierOrderMapper supplierOrderMapper;
    private final OrganizationService organizationService;

    public SupplierOrderServiceImpl(SupplierOrderRepository supplierOrderRepository,
                                    SupplierOrderValidator supplierOrderValidator,
                                    SupplierOrderMapper supplierOrderMapper,
                                    OrganizationService organizationService) {
        this.supplierOrderRepository = supplierOrderRepository;
        this.supplierOrderValidator = supplierOrderValidator;
        this.supplierOrderMapper = supplierOrderMapper;
        this.organizationService = organizationService;
    }

    @Override
    @Transactional
    public Set<SupplierOrderDto> findSupplierOrders(Authentication authentication) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);

        Set<SupplierOrder> supplierOrders = supplierOrderRepository.findByOrgId(orgId);

        return supplierOrders.stream()
                .map(item -> supplierOrderMapper.makeDtoFromSupplierOrder(item))
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public SupplierOrderDto findSupplierOrderByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);

        Optional<SupplierOrder> supplierOrder = supplierOrderRepository.findByUuidAndOrgId(uuid, orgId);

        return supplierOrderMapper.makeDtoFromSupplierOrder(supplierOrder.get());
    }

    @Override
    @Transactional
    public void deleteSupplierOrderByUuid(Authentication authentication, String uuid) {
        SupplierOrderDto supplierOrder = findSupplierOrderByUuid(authentication, uuid);

        if (!supplierOrder.getSupplierOrderStatus().equals(SupplierOrderStatus.CANCELED)) {
            //supplierOrder.setSupplierOrderStatus(SupplierOrderStatus.CANCELED);
        }
    }

    @Override
    @Transactional
    public SupplierOrderDto createSupplierOrder(
            Authentication authentication,
            SupplierOrderDto supplierOrderDto) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        SupplierOrder supplierOrder = supplierOrderMapper.makeSupplierOrderFromDto(orgId, supplierOrderDto);
        supplierOrderValidator.createSupplierOrderValidator(supplierOrder);
        supplierOrderRepository
                .save(supplierOrder);
        this.organizationService.incrementPrefix(orgId);
        return supplierOrderMapper.makeDtoFromSupplierOrder(supplierOrder);
    }

    @Override
    public SupplierOrderDto updateSupplierOrder(
            Authentication authentication, String uuid, SupplierOrderDto supplierOrderDto) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);

        Optional<SupplierOrder> supplierOrderDb = this.supplierOrderRepository.findByUuidAndOrgId(uuid, orgId);

        if (supplierOrderDb.isEmpty()) throw new NoSuchElementException();

        SupplierOrder supplierOrder = supplierOrderMapper.updateSupplierOrder(
                orgId, supplierOrderDto, supplierOrderDb.get()
        );
        supplierOrderRepository.save(supplierOrder);

        return supplierOrderDto;
    }
}
