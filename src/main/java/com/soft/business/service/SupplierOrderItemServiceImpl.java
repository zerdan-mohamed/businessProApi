package com.soft.business.service;

import com.soft.business.dto.SupplierOrderItemDto;
import com.soft.business.exception.FunctionalException;
import com.soft.business.mapper.SupplierOrderItemMapper;
import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderItem;
import com.soft.business.repository.SupplierOrderItemRepository;
import com.soft.business.repository.SupplierOrderRepository;
import com.soft.business.service.organization.OrganizationService;
import com.soft.business.util.ApiErrorCodesConstantes;
import com.soft.business.util.SupplierOrderConstants;
import com.soft.business.util.validator.SupplierOrderItemValidator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SupplierOrderItemServiceImpl implements SupplierOrderItemService{

    private final SupplierOrderItemRepository supplierOrderItemRepository;
    private final SupplierOrderRepository supplierOrderRepository;
    private final SupplierOrderItemValidator supplierOrderItemValidator;
    private final SupplierOrderItemMapper supplierOrderItemMapper;

    public SupplierOrderItemServiceImpl(SupplierOrderItemRepository supplierOrderItemRepository,
                                        SupplierOrderRepository supplierOrderRepository,
                                        SupplierOrderItemValidator supplierOrderItemValidator,
                                        SupplierOrderItemMapper supplierOrderItemMapper) {
        this.supplierOrderItemRepository = supplierOrderItemRepository;
        this.supplierOrderRepository = supplierOrderRepository;
        this.supplierOrderItemValidator = supplierOrderItemValidator;
        this.supplierOrderItemMapper = supplierOrderItemMapper;
    }

    @Override
    @Transactional
    public Set<SupplierOrderItemDto> findSupplierOrderItems(Authentication authentication, String supplierOrderUuid) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);

        Optional<SupplierOrder> supplierOrder = supplierOrderRepository.findByUuidAndOrgId(supplierOrderUuid, orgId);
        List<SupplierOrderItem> supplierOrderItems = supplierOrderItemRepository.findBySupplierOrderAndOrgId(supplierOrder.get(), orgId);

        return supplierOrderItems.stream().map(item -> this.supplierOrderItemMapper.makeDtoFromSupplierOrderItem(orgId, item))
                .collect(Collectors.toSet());
    }

    @Override
    public SupplierOrderItemDto findSupplierOrderItemByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        Optional<SupplierOrderItem> supplierOrderItem = supplierOrderItemRepository.findByUuidAndOrgId(uuid, orgId);

        return supplierOrderItemMapper.makeDtoFromSupplierOrderItem(orgId, supplierOrderItem.get());
    }

    @Override
    @Transactional
    public void deleteSupplierOrderItemByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        SupplierOrderItemDto supplierOrderItem = findSupplierOrderItemByUuid(authentication, uuid);

        if (supplierOrderItem.getSupplierOrder()
                .getSupplierOrderStatus() != SupplierOrderConstants.PAID) {
            long isDeleted = supplierOrderItemRepository.deleteByUuidAndOrgId(uuid, orgId);
        } else throw new FunctionalException(ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_DELETE_STATUS_PAID_EXCEPTION_CODE,
                                             ApiErrorCodesConstantes.SUPPLIER_ORDER_ITEM_DELETE_STATUS_PAID_EXCEPTION_MESSAGE);
    }

    @Override
    @Transactional
    public Set<SupplierOrderItemDto> createSupplierOrderItem(
            Authentication authentication,
            List<SupplierOrderItemDto> supplierOrderItemDto) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        supplierOrderItemDto.stream().forEach(item -> supplierOrderItemValidator.createSupplierOrderItemValidator(item));

        SupplierOrder supplierOrder = this.getSupplierOrder(
                supplierOrderItemDto.get(0), orgId);

        Set<SupplierOrderItem> supplierOrderItem = supplierOrderItemDto.stream()
                .map(item -> supplierOrderItemMapper.makeSupplierOrderItemFromDto(orgId, item, supplierOrder))
                .collect(Collectors.toSet());

        supplierOrderItemRepository.saveAll(supplierOrderItem);

        return supplierOrderItem.stream()
                .map(item -> this.supplierOrderItemMapper.makeDtoFromSupplierOrderItem(orgId, item))
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public SupplierOrderItemDto updateSupplierOrderItem(
            Authentication authentication, String uuid, SupplierOrderItemDto supplierOrderItemDto) {
        int orgId = OrganizationService.getOrgIdFromPrincipal(authentication);
        this.supplierOrderItemValidator.updateSupplierOrderItemValidator(supplierOrderItemDto);

        Optional<SupplierOrderItem> supplierOrderItemDb = this.supplierOrderItemRepository.findByUuidAndOrgId(uuid, orgId);

        SupplierOrderItem supplierOrderItem =
                supplierOrderItemMapper.updateSupplierOrderItem(
                        orgId, supplierOrderItemDto, supplierOrderItemDb.get()
                );
        supplierOrderItemRepository.save(supplierOrderItem);

        return supplierOrderItemMapper.makeDtoFromSupplierOrderItem(orgId, supplierOrderItem);
    }

    private SupplierOrder getSupplierOrder(SupplierOrderItemDto supplierOrderItemDto, int orgId) {
        if (supplierOrderItemDto.getSupplierOrder() != null) {
            Optional<SupplierOrder> oSupplierOrder =
                    this.supplierOrderRepository.findByUuidAndOrgId(
                            supplierOrderItemDto.getSupplierOrder().getUuid(),
                            orgId
                    );
            if (oSupplierOrder.isPresent()) return oSupplierOrder.get();
        }
        return null;
    }
}
